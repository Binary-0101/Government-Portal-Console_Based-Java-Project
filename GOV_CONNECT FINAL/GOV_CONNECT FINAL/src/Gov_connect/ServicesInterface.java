package Gov_connect;

public interface ServicesInterface {
    public void raise_Issue(Register user);
    public void track_Issue(Register register);
    public void aadhaar_Update(Register register,String aadhar_no);
    public void pan_Update(Register register,String pan);
    public void Job_vaccancy(Register register);
    public void apply_Job(Register register);
}