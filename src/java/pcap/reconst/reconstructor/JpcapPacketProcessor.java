package pcap.reconst.reconstructor;

import jpcap.PacketReceiver;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import pcap.reconst.beans.JpcapTcpPacket;

public class JpcapPacketProcessor implements PacketReceiver {
    int packetNumber = 0;
    private PacketReassembler packetReassembler;

    public JpcapPacketProcessor(PacketReassembler packetReassembler) {
        this.packetReassembler = packetReassembler;
    }

    public int getTotalNumberOfPackets() {
        return packetNumber;
    }

    //this method is called every time Jpcap captures a packet
    public void receivePacket(Packet packet) {
        packetNumber++;
        System.out.println(String.format("processing #%d %s", packetNumber, packet));
        packetReassembler.reassemble(new JpcapTcpPacket((TCPPacket) packet));
    }
}

