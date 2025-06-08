package gt.com.pharmacy.util.constant;

public class SecurityConstant {

    private SecurityConstant() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final String ROLE_PREFIX = "ROLE_";
    public static final String AUTHORITY_CREATE = "CREATE";
    public static final String AUTHORITY_READ = "READ";
    public static final String AUTHORITY_UPDATE = "UPDATE";
    public static final String AUTHORITY_DELETE = "DELETE";
}
