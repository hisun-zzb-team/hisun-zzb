package com.hisun.saas.zzb.app.console.shtp.api;

import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01Service;
import com.hisun.saas.zzb.app.console.shpc.service.ShpcService;
import com.hisun.saas.zzb.app.console.shtp.entity.Shtp;
import com.hisun.saas.zzb.app.console.shtp.entity.Shtpsj;
import com.hisun.saas.zzb.app.console.shtp.service.ShtpService;
import com.hisun.saas.zzb.app.console.shtp.vo.ShtpVo;
import com.hisun.saas.zzb.app.console.shtp.vo.ShtpsjVo;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouying on 2017/10/10.
 */

@RestController
@RequestMapping(value="/api/shtp")
public class ShtpRestApi {

    @Resource
    private ShtpService shtpService;
    @Resource
    private ShpcService shpcService;
    @Resource
    private Sha01Service sha01Service;


    @RequestMapping(value = "/save", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Map> add( @RequestBody ShtpVo shtpVo) {
        Map<String,String> resultMap = new HashMap<String,String>();
        Shtp shtp = new Shtp();
        try {
            //判断当前投票器已经投票,如果已投票,则需要先删除原有记录,再增加当前记录
            BeanUtils.copyProperties(shtp,shtpVo);
            String shpcId = shtpVo.getShpcId();
            Shpc shpc = this.shpcService.getByPK(shpcId);
            if(shpc.getShZt()==Shpc.WSH) {
                shtp.setTenant(shpc.getTenant());
                shtp.setShpc(shpc);
                if (shtpVo.getTpsjs() != null) {
                    for (ShtpsjVo tpsjVo : shtpVo.getTpsjs()) {
                        Shtpsj shtpsj = new Shtpsj();
                        BeanUtils.copyProperties(shtpsj, tpsjVo);
                        Sha01 sha01 = this.sha01Service.getByPK(tpsjVo.getSha01Id());
                        shtpsj.setSha01(sha01);
                        shtpsj.setTenant(shpc.getTenant());
                        shtp.add(shtpsj);
                    }
                }
                shtpService.saveByRestApi(shtp);
            }else{
                throw new Exception("当前批次已上会.");
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
