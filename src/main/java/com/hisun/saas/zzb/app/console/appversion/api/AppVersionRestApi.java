package com.hisun.saas.zzb.app.console.appversion.api;

import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.saas.zzb.app.console.appversion.entity.AppVersion;
import com.hisun.saas.zzb.app.console.appversion.service.AppVersionService;
import com.hisun.saas.zzb.app.console.appversion.vo.AppVersionUpdateVo;
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
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * Created by zhouying on 2017/11/23.
 */
@RestController
@RequestMapping(value="/api/app/version")
public class AppVersionRestApi {

    @Resource
    private AppVersionService appVersionService;
    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;


    @RequestMapping(value = "/update/{appCode}", method = RequestMethod.GET)
    @ResponseBody
    public AppVersionUpdateVo update(@PathVariable String appCode, HttpServletRequest req) {
        AppVersionUpdateVo appVersionUpdateVo = new AppVersionUpdateVo();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", AppVersion.TOMBSTONE_FALSE));
            query.add(CommonRestrictions.and(" appCode = :appCode", "appCode",appCode));
            List<AppVersion> appVersionList = appVersionService.list(query,null);
            if(appVersionList!=null && appVersionList.size()>0){
                AppVersion appVersion = appVersionList.get(0);
                appVersionUpdateVo.setName(appVersion.getAppName());
                appVersionUpdateVo.setMd5(appVersion.getAppMd5());
                appVersionUpdateVo.setVersionName(appVersion.getAppVersionName());
                appVersionUpdateVo.setVersionCode(appVersion.getAppVersionCode());
                if(req.getContextPath()!=null){
                    appVersionUpdateVo.setUrl(req.getScheme()
                            +"://"+req.getServerName()
                            +":"+req.getServerPort()
                            +req.getContextPath()
                            +"/api/app/version/update/packet/"+appVersion.getId());
                }else{
                    appVersionUpdateVo.setUrl(req.getScheme()
                            +"://"+req.getServerName()
                            +":"+req.getServerPort()
                            +"/api/app/version/update/packet/"+appVersion.getId());
                }
                appVersionUpdateVo.setDescription("");
                appVersionUpdateVo.setContentLength(appVersion.getAppSize());
                appVersionUpdateVo.setUpdateDate(appVersion.getUpdateDate());
            }

        }catch (Exception e){
            e.printStackTrace();
            return appVersionUpdateVo;
        }
        return appVersionUpdateVo;
    }

    @RequestMapping(value="/update/packet/{id}")
    public void zipDown(@PathVariable String id, HttpServletRequest req, HttpServletResponse resp) throws Exception{
        AppVersion appVersion = this.appVersionService.getByPK(id);
        String zipPath = appVersion.getAppStorePath();
        String fileName = appVersion.getAppName();
        if(appVersion.getAppType()==AppVersion.ANDROID){
            fileName+=".apk";
        }else{
            fileName+=".ipa";
        }
        resp.setContentType("multipart/form-data");
        resp.setHeader("Content-Disposition", "attachment;fileName="+encode(fileName));
        OutputStream output=resp.getOutputStream();
        byte[] b= FileUtils.readFileToByteArray(new File(uploadAbsolutePath+zipPath));
        output.write(b);
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
