package com.hisun.saas.zzb.app.console.appclient.api;

import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.saas.zzb.app.console.appclient.entity.AppClient;
import com.hisun.saas.zzb.app.console.appclient.service.AppClientService;
import com.hisun.saas.zzb.app.console.appclient.vo.AppClientVo;
import com.hisun.util.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/12/12.
 */
@RestController
@RequestMapping(value="/api/app/client")
public class AppClientRestApi {

    @Autowired
    private AppClientService appClientService;

    @RequestMapping(value = "/register/{appCode}", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map> add(@RequestBody AppClientVo clientVo,@PathVariable String appCode) {
        Map<String,String> resultMap = new HashMap<String,String>();
        AppClient client = new AppClient();
        try {
            //判断当前标识是否已经注册,如已经注册,则不需要任何处理
            if(StringUtils.isEmpty(clientVo.getIdentification())==false) {
                CommonConditionQuery query = new CommonConditionQuery();
                query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", AppClient.TOMBSTONE_FALSE));
                query.add(CommonRestrictions.and(" identification = :identification", "identification", clientVo.getIdentification()));
                List<AppClient> list = this.appClientService.list(query,null);
                if(list==null||list.size()==0){
                    client.setIdentification(clientVo.getIdentification());
                    this.appClientService.save(client);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("success","false");
            return new ResponseEntity<Map>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        resultMap.put("success","true");
        return new ResponseEntity<Map>(resultMap, HttpStatus.CREATED);
    }

}
