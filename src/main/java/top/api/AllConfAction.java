package top.api;

import top.core.conference;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.List;

public class AllConfAction extends ActionSupport {
  private List<conference> conferences;
  
  public String allCon() throws ClassNotFoundException, SQLException {
    String[] temp = {conference.publicConf};
    String[] r = {conference.GENUS};
    setConferences(conference.selectConference(r, temp));
    return SUCCESS;
  }

  public List<conference> getConferences() {
    return conferences;
  }

  public void setConferences(List<conference> conferences) {
    this.conferences = conferences;
  } 
  public void aaa() {
	  System.out.println("dfaasa");
  }
}
