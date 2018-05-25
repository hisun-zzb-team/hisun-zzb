package com.hisun.saas.zzb.app.console.gendata.api;

import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.saas.zzb.app.console.gendata.entity.Gendata;
import com.hisun.saas.zzb.app.console.gendata.service.GendataService;
import com.hisun.saas.zzb.app.console.gendata.vo.AppDataVo;
import com.hisun.util.WebUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * Created by zhouying on 2017/11/23.
 */
@RestController
@RequestMapping(value="/api/app/data")
public class AppDataRestApi {

    @Resource
    private GendataService gendataService;
    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;


    @RequestMapping(value = "/update/{appCode}", method = RequestMethod.GET)
    @ResponseBody
    public AppDataVo update(@PathVariable String appCode,HttpServletRequest req) {
        AppDataVo appDataVo = new AppDataVo();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", Gendata.TOMBSTONE_FALSE));
            query.add(CommonRestrictions.and(" isCurrentPacket = :isCurrentPacket", "isCurrentPacket",Gendata.IS_CURRENT ));
            List<Gendata> gendataList =  gendataService.list(query,null);
            if(gendataList!=null && gendataList.size()>0){
                Gendata gendata = gendataList.get(0);
                appDataVo.setName("领导辅助决策App数据包");
                appDataVo.setMd5(gendata.getPacketMd5());
                if(req.getContextPath()!=null){
                    appDataVo.setUrl(req.getScheme()
                            +"://"+req.getServerName()
                            +":"+req.getServerPort()
                            +req.getContextPath()
                            +"/api/app/data/update/packet/"+gendata.getId());
                }else{
                    appDataVo.setUrl(req.getScheme()
                            +"://"+req.getServerName()
                            +":"+req.getServerPort()
                            +"/api/app/data/update/packet/"+gendata.getId());
                }
                appDataVo.setContentLength(gendata.getPacketSize());
                appDataVo.setCreateDate(gendata.getCreateDate());
            }
        }catch (Exception e){
            e.printStackTrace();
            return appDataVo;
        }
        return appDataVo;
    }

    @RequestMapping(value="/update/packet/{id}")
    public void zipDown(@PathVariable String id, HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String zipPath = this.gendataService.getByPK(id).getPath();
        resp.setContentType("multipart/form-data");
        resp.setHeader("Content-Disposition", "attachment;fileName="+encode(GendataService.DATA_PACKET_NAME+".zip"));
        OutputStream output=resp.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(new File(uploadAbsolutePath+zipPath));
        byte[] buffer = new byte[8192];
        int length;
        while ((length = fileInputStream.read(buffer)) != -1) {
            output.write(buffer, 0, length);
        }
        output.flush();
        output.close();
    }

    private String encode(String filename) throws UnsupportedEncodingException {
        if (WebUtil.getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            filename = URLEncoder.encode(filename, "UTF-8");
        } else {
            filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
        }
        return filename;
    }

}
