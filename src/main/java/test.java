import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

public class test extends ActionSupport {
    private String name;
    private int a;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String execute() {
        String method = ServletActionContext.getRequest().getMethod();
        if ("POST".equals(method)){
            a=1;
        }else if ("PUT".equals(method)){
            a=2;
        }else if("DELETE".equals(method)){
            a=3;
        }

        System.out.println(a);
        name = "Hello, " + name + "!";
        return SUCCESS;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}

