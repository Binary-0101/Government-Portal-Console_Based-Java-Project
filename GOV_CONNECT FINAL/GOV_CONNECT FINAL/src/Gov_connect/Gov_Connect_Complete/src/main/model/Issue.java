package main.model;

public class Issue {
    private String minister_dept;
    private int minister_id;
    private String issue_type;
    private String complaint;
    private String status;
    int userId;
    

    public Issue(){

    }
    public Issue(int userId, String issue, int ministerId,String status) {
        this.userId = userId;
        this.complaint = issue;
        this.status = status;
        this.minister_id = ministerId;
    }
    public void setMinisterDept(String dept){
        this.minister_dept = dept;
    }
    public void setMinisterId(int minister_id){
        this.minister_id = minister_id;
    }
    public void setIssueType(String issue_type){
        this.issue_type = issue_type;
    }
    public void setComplaint(String complaint){
        this.complaint = complaint;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public String getMinisterDept(){
        return minister_dept;
    }
    public int getMinisterId(){
        return minister_id;
    }
    public String getIssueType(){
        return issue_type;
    }
    public String getComplaint(){
        return complaint;
    }
    public String getStatus(){
        return status;
    } 
    @Override
    public String toString(){
            return "User ID: " + userId + ", Issue: " + complaint + ", Minister ID: " + minister_id + ", Status: " + status;
        }
    }
