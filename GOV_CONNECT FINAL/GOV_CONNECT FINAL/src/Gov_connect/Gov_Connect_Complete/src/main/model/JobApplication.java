package main.model;

public class JobApplication {
    private int jobId;
    private String qualification;

    public JobApplication(){

    }
    
    public JobApplication(int jobId,String qualification){
        this.jobId = jobId;
        this.qualification = qualification;
    }

    public int getJobId(){
        return jobId;
    }

    public String getQualification(){
        return qualification;
    }
}
