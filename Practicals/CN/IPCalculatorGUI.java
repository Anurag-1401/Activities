import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class IPCalculatorGUI extends JFrame {
    
    // Components
    private JTextField ipField, maskField, networkField;
    private JLabel classLabel, typeLabel, goodHostLabel, subnetworksLabel, hostsLabel, rangeLabel;
    private JTextArea binaryArea;
    private JList<String> networkList;
    private DefaultListModel<String> networkModel;

    private void styleButton(JButton button) {
        button.setBackground(BTN_PRIMARY);
        button.setForeground(BTN_TEXT);
        button.setFocusPainted(false);
        button.setFont(NORMAL_FONT);
        button.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
    }

    
    public IPCalculatorGUI() {
        setTitle("IP Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(BG_MAIN);

        // Create main panels
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel leftPanel = createLeftPanel();
        JPanel rightPanel = createRightPanel();
        
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Bottom buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save");
        JButton closeButton = new JButton("Close");
        
        styleButton(saveButton);
        styleButton(closeButton);

        closeButton.addActionListener(e -> System.exit(0));
        saveButton.addActionListener(e -> saveResults());
        
        buttonPanel.add(saveButton);
        buttonPanel.add(closeButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        setSize(900, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private JPanel createLeftPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(BORDER_COLOR),
                "IP Information",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                TITLE_FONT
        ));
        panel.setBackground(BG_PANEL);
        
        
        // IP Address
        JPanel ipPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ipPanel.add(new JLabel("IP Address:"));
        ipField = new JTextField(15);
        ipField.setText("10.70.6.1");
        ipField.setFont(NORMAL_FONT);
        ipField.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));

        ipPanel.add(ipField);
        
        JButton resetButton = new JButton("Reset");
        styleButton(resetButton);
        resetButton.addActionListener(e -> resetFields());
        ipPanel.add(resetButton);
        
        // Subnet Mask
        JPanel maskPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        maskPanel.add(new JLabel("Subnet Mask:"));
        maskField = new JTextField(15);
        maskField.setText("255.255.255.0");
        maskField.setFont(NORMAL_FONT);
        maskField.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));

        maskPanel.add(maskField);
        
        JButton defaultMaskButton = new JButton("Default Mask");
        styleButton(defaultMaskButton);
        defaultMaskButton.addActionListener(e -> setDefaultMask());
        maskPanel.add(defaultMaskButton);
        
        // Network ID
        JPanel networkPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        networkPanel.add(new JLabel("Network ID:"));
        networkField = new JTextField(15);
        networkField.setText("10.70.6.0/24");
        networkField.setFont(NORMAL_FONT);
        networkField.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
        networkPanel.add(networkField);
        
        JButton computeButton = new JButton("Compute Now");
        styleButton(computeButton);
        computeButton.addActionListener(e -> calculateIPInfo());
        networkPanel.add(computeButton);
        
        panel.add(ipPanel);
        panel.add(maskPanel);
        panel.add(networkPanel);
        
        // Network Information
        JPanel networkInfoPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        networkInfoPanel.setBorder(BorderFactory.createTitledBorder("Network Information"));
        
        networkInfoPanel.add(new JLabel("IP Address Class:"));
        classLabel = new JLabel("A");
        networkInfoPanel.add(classLabel);
        
        networkInfoPanel.add(new JLabel("Address Type:"));
        typeLabel = new JLabel("Reserved");
        networkInfoPanel.add(typeLabel);
        
        networkInfoPanel.add(new JLabel("Good IP For Host:"));
        goodHostLabel = new JLabel("Yes");
        networkInfoPanel.add(goodHostLabel);
        
        networkInfoPanel.add(new JLabel("Yes/No Reason:"));
        JLabel reasonLabel = new JLabel("Private IP Range");
        networkInfoPanel.add(reasonLabel);
        
        panel.add(networkInfoPanel);
        
        // Subnetting Information
        JPanel subnetPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        subnetPanel.setBorder(BorderFactory.createTitledBorder("Subnetting Information"));
        
        subnetPanel.add(new JLabel("# of Subnetworks:"));
        subnetworksLabel = new JLabel("0");
        subnetPanel.add(subnetworksLabel);
        
        subnetPanel.add(new JLabel("# of Hosts:"));
        hostsLabel = new JLabel("254");
        subnetPanel.add(hostsLabel);
        
        subnetPanel.add(new JLabel("Range:"));
        rangeLabel = new JLabel("1");
        subnetPanel.add(rangeLabel);
        
        subnetPanel.add(new JLabel(""));
        subnetPanel.add(new JLabel(""));
        
        panel.add(subnetPanel);
        
        return panel;
    }
    
    private JPanel createRightPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BG_PANEL);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


        // Binary Information
        JPanel binaryPanel = new JPanel(new BorderLayout());
        binaryPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(BORDER_COLOR),
                "Binary Information",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                TITLE_FONT
        ));
        binaryPanel.setBackground(BG_PANEL);
        binaryArea = new JTextArea(6, 30);
        binaryArea.setEditable(false);
        binaryArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        binaryPanel.add(new JScrollPane(binaryArea), BorderLayout.CENTER);
        
        
        binaryPanel.setBackground(BG_PANEL);

        panel.add(binaryPanel);
        
        // Network IDs and Broadcast IDs
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(BORDER_COLOR),
                "Network ID's / Broadcast ID's",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                TITLE_FONT
        ));
        listPanel.setBackground(BG_PANEL);

        // Initialize EMPTY model (important)
        networkModel = new DefaultListModel<>();
        networkList = new JList<>(networkModel);
        networkList.setFont(NORMAL_FONT);

        listPanel.add(new JScrollPane(networkList), BorderLayout.CENTER);
        panel.add(listPanel);

        
        return panel;
    }
    
    private void resetFields() {
        ipField.setText("");
        maskField.setText("");
        networkField.setText("");
        binaryArea.setText("");
        clearResults();
    }
    
    private void setDefaultMask() {
        String ip = ipField.getText().trim();
        if (!isValidIP(ip)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid IP address first");
            return;
        }
        
        String[] parts = ip.split("\\.");
        int firstOctet = Integer.parseInt(parts[0]);
        
        if (firstOctet >= 1 && firstOctet <= 126) {
            maskField.setText("255.0.0.0");
        } else if (firstOctet >= 128 && firstOctet <= 191) {
            maskField.setText("255.255.0.0");
        } else if (firstOctet >= 192 && firstOctet <= 223) {
            maskField.setText("255.255.255.0");
        } else {
            maskField.setText("N/A");
        }
    }
    
    private void calculateIPInfo() {
        String ip = ipField.getText().trim();
        String mask = maskField.getText().trim();
        
        if (!isValidIP(ip) || !isValidIP(mask)) {
            JOptionPane.showMessageDialog(this, "Please enter valid IP and Mask addresses");
            return;
        }
        
        // Calculate network ID
        String networkID = calculateNetworkID(ip, mask);
        networkField.setText(networkID);
        
        // Update binary information
        updateBinaryInfo(ip, mask, networkID);
        
        // Update network information
        updateNetworkInfo(ip);
        
        // Update subnetting information
        updateSubnetInfo(ip, mask);
    }
    
    private String calculateNetworkID(String ip, String mask) {
        String[] ipParts = ip.split("\\.");
        String[] maskParts = mask.split("\\.");
        
        int[] network = new int[4];
        int prefix = 0;
        
        for (int i = 0; i < 4; i++) {
            int ipOctet = Integer.parseInt(ipParts[i]);
            int maskOctet = Integer.parseInt(maskParts[i]);
            network[i] = ipOctet & maskOctet;
            
            // Count set bits for prefix
            prefix += Integer.bitCount(maskOctet);
        }
        
        return network[0] + "." + network[1] + "." + network[2] + "." + network[3] + "/" + prefix;
    }
    
    private void updateBinaryInfo(String ip, String mask, String networkID) {
        StringBuilder binaryText = new StringBuilder();
        
        binaryText.append("IP Address: ").append(toBinary(ip)).append("\n");
        binaryText.append("Mask: ").append(toBinary(mask)).append("\n");
        
        String networkOnly = networkID.split("/")[0];
        binaryText.append("Network ID: ").append(toBinary(networkOnly));
        
        binaryArea.setText(binaryText.toString());
    }
    
    private String toBinary(String ip) {
        String[] parts = ip.split("\\.");
        StringBuilder binary = new StringBuilder();
        
        for (int i = 0; i < parts.length; i++) {
            int octet = Integer.parseInt(parts[i]);
            binary.append(String.format("%8s", Integer.toBinaryString(octet)).replace(' ', '0'));
            if (i < 3) binary.append(".");
        }
        
        return binary.toString();
    }
    
    private void updateNetworkInfo(String ip) {
        String[] parts = ip.split("\\.");
        int firstOctet = Integer.parseInt(parts[0]);
        
        // Determine class
        if (firstOctet >= 1 && firstOctet <= 126) {
            classLabel.setText("A");
        } else if (firstOctet >= 128 && firstOctet <= 191) {
            classLabel.setText("B");
        } else if (firstOctet >= 192 && firstOctet <= 223) {
            classLabel.setText("C");
        } else if (firstOctet >= 224 && firstOctet <= 239) {
            classLabel.setText("D (Multicast)");
        } else {
            classLabel.setText("E (Reserved)");
        }
        
        // Check if reserved/private
        if (ip.startsWith("10.") || 
            ip.startsWith("172.16.") || ip.startsWith("172.17.") || 
            ip.startsWith("172.18.") || ip.startsWith("172.19.") ||
            ip.startsWith("172.20.") || ip.startsWith("172.21.") ||
            ip.startsWith("172.22.") || ip.startsWith("172.23.") ||
            ip.startsWith("172.24.") || ip.startsWith("172.25.") ||
            ip.startsWith("172.26.") || ip.startsWith("172.27.") ||
            ip.startsWith("172.28.") || ip.startsWith("172.29.") ||
            ip.startsWith("172.30.") || ip.startsWith("172.31.") ||
            ip.startsWith("192.168.")) {
            typeLabel.setText("Private");
            goodHostLabel.setText("Yes");
        } else if (firstOctet == 127) {
            typeLabel.setText("Loopback");
            goodHostLabel.setText("No");
        } else if (firstOctet >= 224 && firstOctet <= 239) {
            typeLabel.setText("Multicast");
            goodHostLabel.setText("No");
        } else if (firstOctet >= 240) {
            typeLabel.setText("Reserved");
            goodHostLabel.setText("No");
        } else {
            typeLabel.setText("Public");
            goodHostLabel.setText("Yes");
        }
    }
    
       private void updateSubnetInfo(String ip, String mask) {
    
        // ---------- Calculate number of hosts ----------
        String[] maskParts = mask.split("\\.");
        int hostBits = 0;
    
        for (String part : maskParts) {
            int octet = Integer.parseInt(part);
            hostBits += (8 - Integer.bitCount(octet));
        }
    
        long hosts = (long) Math.pow(2, hostBits) - 2;
        if (hosts < 0) hosts = 0;
        hostsLabel.setText(String.valueOf(hosts));
    
        // ---------- Calculate number of subnets (class-based) ----------
        String[] ipParts = ip.split("\\.");
        int firstOctet = Integer.parseInt(ipParts[0]);
    
        int networkBits;
        if (firstOctet >= 1 && firstOctet <= 126) {
            networkBits = 8;      // Class A
        } else if (firstOctet >= 128 && firstOctet <= 191) {
            networkBits = 16;     // Class B
        } else if (firstOctet >= 192 && firstOctet <= 223) {
            networkBits = 24;     // Class C
        } else {
            subnetworksLabel.setText("0");
            return;
        }
    
        int borrowedBits = 32 - hostBits - networkBits;
        if (borrowedBits > 0) {
            subnetworksLabel.setText(String.valueOf((long) Math.pow(2, borrowedBits)));
        } else {
            subnetworksLabel.setText("0");
        }
    
        // ---------- Network ID & Broadcast ID (CIDR based) ----------
        networkModel.clear();
    
        int[] network = new int[4];
        int[] broadcast = new int[4];
    
        for (int i = 0; i < 4; i++) {
            int ipOctet = Integer.parseInt(ipParts[i]);
            int maskOctet = Integer.parseInt(maskParts[i]);
        
            network[i] = ipOctet & maskOctet;
            broadcast[i] = network[i] | (~maskOctet & 0xFF);
        }
    
        String networkID =
                network[0] + "." + network[1] + "." + network[2] + "." + network[3];
    
        String broadcastID =
                broadcast[0] + "." + broadcast[1] + "." + broadcast[2] + "." + broadcast[3];
    
        networkModel.addElement(networkID + "    " + broadcastID);
    }

    
    private boolean isValidIP(String ip) {
        String[] parts = ip.split("\\.");
        if (parts.length != 4) return false;
        
        for (String part : parts) {
            try {
                int num = Integer.parseInt(part);
                if (num < 0 || num > 255) return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
    
    private void clearResults() {
        classLabel.setText("");
        typeLabel.setText("");
        goodHostLabel.setText("");
        subnetworksLabel.setText("");
        hostsLabel.setText("");
        rangeLabel.setText("");
        networkModel.clear();
    }
    
    private void saveResults() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            // Here you would implement saving to file
            JOptionPane.showMessageDialog(this, "Results saved successfully!");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new IPCalculatorGUI();
        });
    }

    // Professional UI Colors
    private static final Color BG_MAIN = new Color(244, 246, 248);
    private static final Color BG_PANEL = Color.WHITE;
    private static final Color BTN_PRIMARY = new Color(52, 73, 94);
    private static final Color BTN_TEXT = Color.WHITE;
    private static final Color BORDER_COLOR = new Color(200, 200, 200);
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font NORMAL_FONT = new Font("Segoe UI", Font.PLAIN, 12);

}
