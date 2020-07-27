package cn.pyj520.shop.api.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * JWT校验工具类
 * <ol>
 * <li>iss: jwt签发者</li>
 * <li>sub: jwt所面向的用户</li>
 * <li>aud: 接收jwt的一方</li>
 * <li>exp: jwt的过期时间，这个过期时间必须要大于签发时间</li>
 * <li>nbf: 定义在什么时间之前，该jwt都是不可用的</li>
 * <li>iat: jwt的签发时间</li>
 * <li>jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击</li>
 * </ol>
 */

public class JWTUtil {

    public static final long REFRESHTOKEN_EXPIRED_TIME = 30 * 24 * 60 * 60 * 1000;

    public static final long ACESSTOKEN_EXPIRED_TIME = 60 * 60 * 1000;

    private Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    public static final String jwtId = "756752342";

    /**
     * jwt 加密解密密钥
     */
    private static final String JWT_SECRET = "*jds&egw%uE#ncsy^pt";


    public final static String JWT_USER_ID = "userId";

    public final static String JWT_TOKEN_TYPE = "tokenType";

    public final static String ACCESS_TOKEN = "accessToken";

    public final static String REFRESH_TOKEN = "token";

    /**
     * 创建JWT
     */
    public static String createJWT(Map<String, Object> claims, Long time) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; //指定签名的时候使用的签名算法，也就是header那部分，jwt已经将这部分内容封装好了。
        Date now = new Date(System.currentTimeMillis());

        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();//生成JWT的时间
        //下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder() //这里其实就是new一个JwtBuilder，设置jwt的body
                .setClaims(claims)          //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setId(jwtId)                  //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(now)           //iat: jwt的签发时间
                .signWith(signatureAlgorithm, secretKey);//设置签名使用的签名算法和签名使用的秘钥
        if (time >= 0) {
            long expMillis = nowMillis + time;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);     //设置过期时间
        }
        return builder.compact();
    }


    /**
     * 验证jwt
     */
    public static Claims verifyJwt(String token) {
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey();
        Claims claims;
        try {
            claims = Jwts.parser()  //得到DefaultJwtParser
                    .setSigningKey(key)         //设置签名的秘钥
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }//设置需要解析的jwt
        return claims;

    }

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generalKey() {
        String stringKey = JWT_SECRET;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 根据userId和openid生成token
     * type 生成的是token类型，区分accessToken or token
     */
    public static String generateToken(String type, Integer uid, Long time) {
        Map<String, Object> map = new HashMap<>();
        map.put(JWT_USER_ID, String.valueOf(uid));
        map.put(JWT_TOKEN_TYPE, type);
        return createJWT(map, time);
    }

    /*项目验证token 2019/05/27出现存入时Long类型的uid，取出时变成Integer导致类型转换错误，
    * 所以做特殊处理，存入String，对String进行类型转换，造成原因，时间原因，暂时不详
    * */
    public static Integer verifyJwtByType(String token, String type) {
        Claims claims = verifyJwt(token);
        if (NullUtil.isNullObject(claims)) {
            return null;
        }
        String tokenType = (String) claims.get(JWT_TOKEN_TYPE);
        if (!type.equals(tokenType)) {
            return null;
        }
        String uid =(String) claims.get(JWT_USER_ID);
        return Integer.valueOf(uid);
    }


    public static void main(String[] args) {
        String test01 = generateToken("accessToken", 123456, JWTUtil.ACESSTOKEN_EXPIRED_TIME);
        System.out.println(test01);

        Claims claims = verifyJwt(test01);
        System.out.println(claims);
    }


}