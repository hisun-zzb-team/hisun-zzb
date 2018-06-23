/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gendata.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.apiregister.entity.ApiRegister;
import com.hisun.saas.zzb.app.console.apiregister.service.ApiRegisterService;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA01Service;
import com.hisun.saas.zzb.app.console.gbcx.service.GbcxService;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMc;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcService;
import com.hisun.saas.zzb.app.console.gbtj.entity.Gbtj;
import com.hisun.saas.zzb.app.console.gbtj.service.GbtjService;
import com.hisun.saas.zzb.app.console.gendata.dao.GendataDao;
import com.hisun.saas.zzb.app.console.gendata.entity.DataPacketContent;
import com.hisun.saas.zzb.app.console.gendata.entity.Gendata;
import com.hisun.saas.zzb.app.console.gendata.service.GendataService;
import com.hisun.saas.zzb.app.console.gendata.vo.GendataVo;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.saas.zzb.app.console.shpc.service.ShpcService;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.util.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class GendataServiceImpl extends BaseServiceImpl<Gendata, String> implements GendataService {

    @Resource
    private ShpcService shpcService;
    @Resource
    private GbtjService gbtjService;
    @Resource
    private GbMcService gbMcService;
    @Resource
    private ApiRegisterService apiRegisterService;
    @Resource
    private GbcxService gbcxService;



    @Value("${sys.upload.absolute.path}")
    private String uploadAbsolutePath;


    private GendataDao gendataDao;

    @Autowired
    public void setBaseDao(BaseDao<Gendata, String> gendataDao) {
        this.baseDao = gendataDao;
        this.gendataDao = (GendataDao) gendataDao;
    }

    public String saveAppInitData(Gendata gendata) throws Exception {
        //初始化数据目录
        String uuid = UUIDUtil.getUUID();
        String dataDir = uploadAbsolutePath + GendataService.DATA_PATH + uuid;
        String appDataZipPath = GendataService.DATA_PATH + UUIDUtil.getUUID() + ".zip";
        String appDataZipRealPath = uploadAbsolutePath + appDataZipPath;

        List<String> dirs = new ArrayList<>();
        String dbdir = dataDir + GendataService.DB_PATH;
        dirs.add(dbdir);
        String imgdir = dataDir + GendataService.IMG_PATH;
        dirs.add(imgdir);
        String attsdir = dataDir + GendataService.ATTS_PATH;
        dirs.add(attsdir);
        //初始化非机构化数据存储目录
        this.initAppStoreDir(dirs);
        String sqliteDB = dbdir + File.separator+GendataService.SQLITE_DB_NAME;
        //初始化sqlite数据库
        this.initAppSqliteDB(sqliteDB);
        //生成配置数据包
        this.newAppConfigData(sqliteDB);

        //压缩数据文件
        CompressUtil.zip(appDataZipRealPath, dataDir+File.separator, GendataService.DATA_PACKET_NAME);
        gendata.setPath(appDataZipPath);

        File f = new File(appDataZipRealPath);
        FileInputStream inputStream = new FileInputStream(f);
        gendata.setPacketMd5(DigestUtils.md5Hex(IOUtils.toByteArray(inputStream)));
        gendata.setPacketSize(Long.toString(f.length()));
        inputStream.close();
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        EntityWrapper.wrapperSaveBaseProperties(gendata,userLoginDetails);
        FileUtils.deleteDirectory(new File(dataDir+File.separator));
        return this.save(gendata);
    }


    @Override
    public String saveAppData(Gendata gendata, Map<String, String> selectedMap) throws Exception {
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        //初始化数据目录
        String uuid = UUIDUtil.getUUID();
        String dataDir = uploadAbsolutePath + GendataService.DATA_PATH + uuid ;
        String appDataZipPath = GendataService.DATA_PATH + UUIDUtil.getUUID() + ".zip";
        String appDataZipRealPath = uploadAbsolutePath + appDataZipPath;

        List<String> dirs = new ArrayList<>();
        String dbdir = dataDir + GendataService.DB_PATH;
        dirs.add(dbdir);
        String imgdir = dataDir + GendataService.IMG_PATH;
        dirs.add(imgdir);
        String attsdir = dataDir + GendataService.ATTS_PATH;
        dirs.add(attsdir);
        //初始化非机构化数据存储目录
        this.initAppStoreDir(dirs);
        String sqliteDB = dbdir + File.separator+GendataService.SQLITE_DB_NAME;
        //初始化sqlite数据库
        this.initAppSqliteDB(sqliteDB);
        //根据页面选择,生成业务数据包
        this.newAppData(gendata,selectedMap,sqliteDB,imgdir,attsdir);
        //生成配置数据包
        this.newAppConfigData(sqliteDB);
        //压缩数据文件
        CompressUtil.zip(appDataZipRealPath, dataDir+File.separator, GendataService.DATA_PACKET_NAME);
        gendata.setPath(appDataZipPath);

        File f = new File(appDataZipRealPath);
        //FileInputStream inputStream = new FileInputStream(f);
        //gendata.setPacketMd5(DigestUtils.md5Hex(IOUtils.toByteArray(inputStream)));
        gendata.setPacketMd5(Md5Util.getMD5(f));
        gendata.setPacketSize(Long.toString(f.length()));

        EntityWrapper.wrapperSaveBaseProperties(gendata,userLoginDetails);
        FileUtils.deleteDirectory(new File(dataDir+File.separator));
        return this.save(gendata);
    }


    private void newAppData(Gendata gendata, Map<String, String> selectedMap,
                            String sqliteDB, String imgdir, String attsdir) throws Exception {
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        if (selectedMap != null && selectedMap.size() > 0) {
            for (Iterator<String> it = selectedMap.keySet().iterator(); it.hasNext(); ) {
                String key = it.next();
                String value = selectedMap.get(key);
                String[] ids = value.split(",");
                if (key.equals(GendataVo.SHPC_DATA)) {
                    for (String id : ids) {
                        Shpc shpc = this.shpcService.getPK(id);
                        DataPacketContent dataPacketContent = new DataPacketContent();
                        dataPacketContent.setDataId(id);
                        dataPacketContent.setGendata(gendata);
                        dataPacketContent.setDataType(DataPacketContent.SHPC_DATA);
                        dataPacketContent.setSort(shpc.getPx());
                        dataPacketContent.setName(shpc.getPcmc());
                        dataPacketContent.setTenant(userLoginDetails.getTenant());
                        gendata.addDataPacketContent(dataPacketContent);
                        this.shpcService.saveAsSqlite(id,sqliteDB,imgdir,attsdir);
                    }
                } else if (key.equals(GendataVo.GBTJ_DATA)) {
                    for (String id : ids) {
                        Gbtj gbtj = this.gbtjService.getPK(id);
                        DataPacketContent dataPacketContent = new DataPacketContent();
                        dataPacketContent.setDataId(id);
                        dataPacketContent.setGendata(gendata);
                        dataPacketContent.setDataType(DataPacketContent.GBTJ_DATA);
                        dataPacketContent.setSort(gbtj.getPx());
                        dataPacketContent.setName(gbtj.getTjmc());
                        dataPacketContent.setTenant(userLoginDetails.getTenant());
                        gendata.addDataPacketContent(dataPacketContent);
                        this.gbtjService.saveAsSqlite(id, sqliteDB,imgdir,attsdir);
                    }
                } else if (key.equals(GendataVo.GBMC_DATA)) {
                    for (String id : ids) {
                        GbMc gbMc = this.gbMcService.getPK(id);
                        DataPacketContent dataPacketContent = new DataPacketContent();
                        dataPacketContent.setDataId(id);
                        dataPacketContent.setGendata(gendata);
                        dataPacketContent.setDataType(DataPacketContent.GBMC_DATA);
                        dataPacketContent.setSort(gbMc.getPx());
                        dataPacketContent.setName(gbMc.getMc());
                        dataPacketContent.setTenant(userLoginDetails.getTenant());
                        gendata.addDataPacketContent(dataPacketContent);
                        this.gbMcService.saveAsSqlite(id,sqliteDB,imgdir,attsdir);
                    }
                }else if (key.equals(GendataVo.GBCX_DATA)) {
                    //生成干部查询数据包
                    this.gbcxService.saveAsSqlite(sqliteDB,imgdir,attsdir);
                    DataPacketContent dataPacketContent = new DataPacketContent();
                    dataPacketContent = new DataPacketContent();
                    dataPacketContent.setDataId(GendataVo.GBCX_DATA);
                    dataPacketContent.setGendata(gendata);
                    dataPacketContent.setDataType(DataPacketContent.GBCX_DATA);
                    dataPacketContent.setSort(2);
                    dataPacketContent.setName("干部查询");
                    dataPacketContent.setTenant(userLoginDetails.getTenant());
                    gendata.addDataPacketContent(dataPacketContent);
                }else if (key.equals(GendataVo.ZSCX_DATA)) {
                    //生成职数查询数据包
//                    this.gbcxService.saveAsSqlite(sqliteDB);
                    DataPacketContent dataPacketContent = new DataPacketContent();
                    dataPacketContent = new DataPacketContent();
                    dataPacketContent.setDataId(GendataVo.ZSCX_DATA);
                    dataPacketContent.setGendata(gendata);
                    dataPacketContent.setDataType(DataPacketContent.ZSCX_DATA);
                    dataPacketContent.setSort(3);
                    dataPacketContent.setName("职数查询");
                    dataPacketContent.setTenant(userLoginDetails.getTenant());
                    gendata.addDataPacketContent(dataPacketContent);
                }

            }

        }
    }


    public  String saveAppDataFromAnotherAppData(Gendata newPacket,Map<String,String> selectedMap,
                                         Gendata oldPacket,Map<String,String> selectedMapFromOldPacket)throws Exception{
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        if(oldPacket!=null && selectedMapFromOldPacket!=null
                &&selectedMapFromOldPacket.size()>0){
            File oldPacketFile = new File(uploadAbsolutePath+oldPacket.getPath());
            if(oldPacketFile.exists()){
                //解压原有数据包到指定目录
                String uuid = UUIDUtil.getUUID();
                String unzipDir = uploadAbsolutePath + GendataService.DATA_PATH + uuid;
                CompressUtil.unzip(oldPacketFile.getAbsolutePath(),unzipDir+File.separator);
                String oldDataDir = unzipDir+File.separator+GendataService.DATA_PACKET_NAME+File.separator;
                List<String> dirs = new ArrayList<>();
                String imgdir = oldDataDir+ GendataService.IMG_PATH;
                dirs.add(imgdir);
                String attsdir = oldDataDir+ GendataService.ATTS_PATH;
                dirs.add(attsdir);
                this.initAppStoreDir(dirs);
                String sqliteDB = oldDataDir+GendataService.DB_PATH +GendataService.SQLITE_DB_NAME;

                //根据用户所选择的数据进行保留,其他的删除
                if(selectedMapFromOldPacket.containsKey(GendataVo.SHPC_DATA)==false){
                    this.clearShpcDataInSqliteDB(sqliteDB,oldDataDir);
                }else{
                    //如果选择了,则生成DataPacketContent
                    //没有被选择数据需要清除掉
                    String idStr = selectedMapFromOldPacket.get(GendataVo.SHPC_DATA);
                    List<String> ids = Arrays.asList(idStr.split(","));
                    List<DataPacketContent> shpcDataPacketContents = oldPacket.getShpcDataPacketContents();
                    for(DataPacketContent dataPacketContent : shpcDataPacketContents){
//                        if(ids.contains(dataPacketContent.getDataId())==false){
//
//                        }else{
                            DataPacketContent newDataPacketContent = new DataPacketContent();
                            newDataPacketContent.setDataId(dataPacketContent.getDataId());
                            newDataPacketContent.setDataType(DataPacketContent.SHPC_DATA);
                            newDataPacketContent.setSort(dataPacketContent.getSort());
                            newDataPacketContent.setName(dataPacketContent.getName());
                            EntityWrapper.wrapperSaveBaseProperties(newDataPacketContent,userLoginDetails);
                            newPacket.addDataPacketContent(newDataPacketContent);
//                        }
                    }
                }
                if(selectedMapFromOldPacket.containsKey(GendataVo.GBMC_DATA)==false){
                    this.clearGbmcDataInSqliteDB(sqliteDB,oldDataDir);
                }else{
                    List<DataPacketContent> gbmcDataPacketContents = oldPacket.getGbmcDataPacketContents();
                    for(DataPacketContent dataPacketContent : gbmcDataPacketContents){
                        DataPacketContent newDataPacketContent = new DataPacketContent();
                        newDataPacketContent.setDataId(dataPacketContent.getDataId());
                        newDataPacketContent.setDataType(DataPacketContent.GBMC_DATA);
                        newDataPacketContent.setSort(dataPacketContent.getSort());
                        newDataPacketContent.setName(dataPacketContent.getName());
                        EntityWrapper.wrapperSaveBaseProperties(newDataPacketContent,userLoginDetails);
                        newPacket.addDataPacketContent(newDataPacketContent);
                    }
                }
                if(selectedMapFromOldPacket.containsKey(GendataVo.GBCX_DATA)==false) {
                    this.clearGbcxDataInSqliteDB(sqliteDB,oldDataDir);
                }else{
                    List<DataPacketContent> gbcxDataPacketContents = oldPacket.getGbcxDataPacketContents();
                    for(DataPacketContent dataPacketContent : gbcxDataPacketContents){
                        DataPacketContent newDataPacketContent = new DataPacketContent();
                        newDataPacketContent.setDataId(dataPacketContent.getDataId());
                        newDataPacketContent.setDataType(DataPacketContent.GBCX_DATA);
                        newDataPacketContent.setSort(dataPacketContent.getSort());
                        newDataPacketContent.setName(dataPacketContent.getName());
                        EntityWrapper.wrapperSaveBaseProperties(newDataPacketContent,userLoginDetails);
                        newPacket.addDataPacketContent(newDataPacketContent);
                    }

                }
                if(selectedMapFromOldPacket.containsKey(GendataVo.GBTJ_DATA)==false){
                    this.clearGbtjDataInSqliteDB(sqliteDB,oldDataDir);
                }else{
                    List<DataPacketContent> gbtjDataPacketContents = oldPacket.getGbtjDataPacketContents();
                    for(DataPacketContent dataPacketContent : gbtjDataPacketContents){
                        DataPacketContent newDataPacketContent = new DataPacketContent();
                        newDataPacketContent.setDataId(dataPacketContent.getDataId());
                        newDataPacketContent.setDataType(DataPacketContent.GBTJ_DATA);
                        newDataPacketContent.setSort(dataPacketContent.getSort());
                        newDataPacketContent.setName(dataPacketContent.getName());
                        EntityWrapper.wrapperSaveBaseProperties(newDataPacketContent,userLoginDetails);
                        newPacket.addDataPacketContent(newDataPacketContent);
                    }
                }
                //处理新选择的数据进行增加
                this.newAppData(newPacket,selectedMap,sqliteDB,imgdir,attsdir);

                String appDataZipPath = GendataService.DATA_PATH + UUIDUtil.getUUID() + ".zip";
                String appDataZipRealPath = uploadAbsolutePath + appDataZipPath;
                //压缩数据文件
                CompressUtil.zip(appDataZipRealPath, oldDataDir, GendataService.DATA_PACKET_NAME);
                newPacket.setPath(appDataZipPath);

                File f = new File(appDataZipRealPath);
                newPacket.setPacketMd5(Md5Util.getMD5(f));
                newPacket.setPacketSize(Long.toString(f.length()));
                EntityWrapper.wrapperSaveBaseProperties(newPacket,userLoginDetails);
                FileUtils.deleteDirectory(new File(unzipDir));
                return this.save(newPacket);
            }else {
                return "";
            }
        }else{
            return this.saveAppData(newPacket,selectedMap);
        }

    }

    private void clearShpcDataInSqliteDB(String sqliteDB,String storePath) throws SQLException,
            ClassNotFoundException, IOException {
        SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
        List<String> clearSqls = new ArrayList<String>();
        clearSqls.add("delete from app_sh_pc_atts");
        clearSqls.add("delete from app_sh_a01_shgx");
        clearSqls.add("delete from app_sh_a01_gzjl");
        clearSqls.add("delete from app_sh_a01_kccl");
        clearSqls.add("delete from app_sh_a01_gbrmspb");
        clearSqls.add("delete from app_sh_a01_dascqk_tips");
        clearSqls.add("delete from app_sh_a01_dascqk");
        clearSqls.add("delete from app_sh_a01_grzdsx");
        clearSqls.add("delete from app_sh_a01_jc");
        clearSqls.add("delete from app_sh_a01_ndkh");
        clearSqls.add("delete from app_sh_a01");
        clearSqls.add("delete from app_sh_pc");
        for(String sql : clearSqls){
            sqliteDBUtil.update(sqliteDB,sql);
        }
        String attspath = storePath+GendataService.ATTS_PATH+ShpcService.ATTS_PATH;
        String imgpath = storePath+GendataService.IMG_PATH+ShpcService.ATTS_PATH;
        FileUtils.deleteDirectory(new File(attspath));
        FileUtils.deleteDirectory(new File(imgpath));
    }

    private void clearGbmcDataInSqliteDB(String sqliteDB,String storePath) throws SQLException,
            ClassNotFoundException, IOException {
        SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
        List<String> clearSqls = new ArrayList<String>();
        clearSqls.add("delete from app_mc_a01_gzjl");
        clearSqls.add("delete from app_mc_a01_gbrmspb");
        clearSqls.add("delete from app_mc_a01");
        clearSqls.add("delete from app_mc_b01");
        clearSqls.add("delete from app_mc");
        for(String sql : clearSqls){
            sqliteDBUtil.update(sqliteDB,sql);
        }
        FileUtils.deleteDirectory(new File(storePath+GendataService.ATTS_PATH+GbMcService.ATTS_PATH));
    }

    private void clearGbtjDataInSqliteDB(String sqliteDB,String storePath) throws SQLException,
            ClassNotFoundException, IOException {
        SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
        List<String> clearSqls = new ArrayList<String>();
        clearSqls.add("delete from app_dwjg_tj");
        for(String sql : clearSqls){
            sqliteDBUtil.update(sqliteDB,sql);
        }
    }


    private void clearGbcxDataInSqliteDB(String sqliteDB,String storePath) throws SQLException,
            ClassNotFoundException, IOException {
        SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
        List<String> clearSqls = new ArrayList<String>();
        clearSqls.add("delete from app_zscx_zs");
        clearSqls.add("delete from app_zscx_zs_a01");
        clearSqls.add("delete from app_aset_a02");
        clearSqls.add("delete from app_aset_a01");
        clearSqls.add("delete from app_bset_fl_2_b01");
        clearSqls.add("delete from app_bset_b01");
        clearSqls.add("delete from app_bset_fl");
        for(String sql : clearSqls){
            sqliteDBUtil.update(sqliteDB,sql);
        }
        FileUtils.deleteDirectory(new File(storePath+GendataService.ATTS_PATH+AppAsetA01Service.ATTS_PATH));
        FileUtils.deleteDirectory(new File(storePath+GendataService.IMG_PATH+AppAsetA01Service.ZP_PATH));
    }

    private void initAppStoreDir(List<String> dirs) {
        if (dirs != null) {
            for (String s : dirs) {
                //初始化数据存储目录
                File dir = new File(s);
                if (dir.exists() == false) {
                    dir.mkdirs();
                }
            }
        }

    }

    private void newAppConfigData(String sqlite) throws Exception {
        SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
        //api数据
        List<ApiRegister> apiRegisters = this.apiRegisterService.list();
        if (apiRegisters != null && apiRegisters.size() > 0) {
            for (ApiRegister apiRegister : apiRegisters) {
                sqliteDBUtil.insert(sqlite, apiRegister.toSqliteInsertSql());
            }
        }
    }

    private void initAppSqliteDB(String sqlite) throws Exception {

        SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
        Statement statement = sqliteDBUtil.createDatabase(sqlite);

        InputStream inputStream = GendataServiceImpl.class.getClassLoader().getResourceAsStream("zzb-app.sql");
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader in = new BufferedReader(isr);
        int len = 0;
        StringBuffer sb = new StringBuffer("");
        String line = null;
        while ((line = in.readLine()) != null) {

            if (len != 0) {
                sb.append(line);
            } else {
                sb.append(line);
            }
            len++;
        }
        in.close();
        isr.close();
        inputStream.close();
        sqliteDBUtil.createTables(sqlite, sb.toString());
        if(statement!=null) {
            sqliteDBUtil.closeStatement(statement);
        }
    }


    @Override
    public void delete(Gendata gendata){
        if(StringUtils.isEmpty(gendata.getPath())==false){
            try {
                FileUtils.forceDelete(new File(uploadAbsolutePath + gendata.getPath()));
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        this.gendataDao.delete(gendata);

    }

    @Override
    public void deleteByPK(String id){
        Gendata gendata = this.gendataDao.getByPK(id);
        if(StringUtils.isEmpty(gendata.getPath())==false){
            try {
                FileUtils.forceDelete(new File(uploadAbsolutePath + gendata.getPath()));
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        this.gendataDao.delete(gendata);
    }

}
