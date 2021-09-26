# desensitized-springboot-starter
数据脱敏快速方案，starter方式轻松集成springboot，使用无感
### 快速使用
##### 一、 ~~引入starter包（未上传中央仓库可忽略）~~
```xml
<dependency>
    <groupId>com.ke.acecandy</groupId>
    <artifactId>desensitized-springboot-starter</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```
##### 二、对象字段上加入脱敏注解
```java
import static com.ke.acecandy.desensitize.impl.DesensitizeEnum.*;
import com.ke.acecandy.desensitize.autoconfig.Desensitize;

@Data
public class User {

    private Long id;

    @Desensitize(CHINESE_NAME)
    private String username;

    @Desensitize(PASSWORD)
    private String password;

    @Desensitize(FIXED_PHONE)
    private String fixedPhone;

    @Desensitize(ID_CARD)
    private Long idCard;

    @Desensitize(MOBILE_PHONE)
    private Long mobilePhone;

    @Desensitize(EMAIL)
    private String email;

    @Desensitize(BANK_CARD)
    private Long bankCard;

    @Desensitize(CAR_LICENSE)
    private String carLicense;

    @Desensitize(ADDRESS)
    private String address;

    @Desensitize(CUSTOM, start = 1, end = 2)
    private String customStr;
}
```