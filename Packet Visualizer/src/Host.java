public class Host {

    private String IP_address;
    private int    packet_size;
    private double timeStamp;


    public Host(String IP_address, int packet_size, double timeStamp) {

        this.IP_address = IP_address;
        this.packet_size = packet_size;
        this.timeStamp = timeStamp;
    }


    public Host() {
    }



    public String getIP_address() {
        return IP_address;
    }

    public void setIP_address(String IP_address) {
        this.IP_address = IP_address;
    }

    public int getPacket_size() {
        return packet_size;
    }

    public void setPacket_size(int packet_size) {
        this.packet_size = packet_size;
    }

    public double getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(double timeStamp) {
        this.timeStamp = timeStamp;
    }
}
