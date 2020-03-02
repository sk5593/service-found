package com.service.found.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.service.found.config.BizException;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;
@Slf4j
public class JwtUtil {
    private static final byte[] SECRET = "6MNSobBRCHGIO0fS6MNSobBRCHGIO0fS".getBytes();

    /**
     * 过期时间一个周
     */
    private static final long EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;
    public static String buildJWT(String account) {
        try {
            /**
             * 1.创建一个32-byte的密匙
             */
            MACSigner macSigner = new MACSigner(SECRET);
            /**
             * 2. 建立payload 载体
             */
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject("doi")
                    .issuer("http://www.doiduoyi.com")
                    .claim("ACCOUNT",account)
                    .expirationTime(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                    .build();

            /**
             * 3. 建立签名
             */
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(macSigner);

            /**
             * 4. 生成token
             */
            String token = signedJWT.serialize();
            return token;
        } catch (KeyLengthException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String vaildToken(String token ) throws BizException {
        try {
            SignedJWT jwt = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SECRET);
            //校验是否有效
            if (!jwt.verify(verifier)) {
                throw new BizException("Token 无效");
            }

            //校验超时
            Date expirationTime = jwt.getJWTClaimsSet().getExpirationTime();
            if (new Date().after(expirationTime)) {
                throw new BizException("Token 过期");
            }

            //获取载体中的数据
            Object account = jwt.getJWTClaimsSet().getClaim("ACCOUNT");
            //是否有openUid
            if (Objects.isNull(account)){
                throw new BizException("账号为空");
            }
            return account.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return null;
    }
}
