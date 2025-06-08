package gt.com.pharmacy.util.constant;

import org.springframework.stereotype.Component;

@Component("permissionConstants")
public class PermissionConstants {

    public String create() {
        return SecurityConstant.AUTHORITY_CREATE;
    }

    public String read() {
        return SecurityConstant.AUTHORITY_READ;
    }

    public String update() {
        return SecurityConstant.AUTHORITY_UPDATE;
    }

    public String delete() {
        return SecurityConstant.AUTHORITY_DELETE;
    }
}
