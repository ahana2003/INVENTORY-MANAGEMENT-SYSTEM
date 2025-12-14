import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.net.URL;

public class InventoryGUI extends JFrame {

    DefaultTableModel model;
    Font montserrat;

    public InventoryGUI() {

        // ===== LOAD MONTSERRAT FONT =====
        try {
            URL fontUrl = new URL("https://github.com/google/fonts/raw/main/ofl/montserrat/Montserrat-Regular.ttf");
            InputStream is = fontUrl.openStream();
            montserrat = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(17f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(montserrat);
        } catch (Exception e) {
            montserrat = new Font("Segoe UI", Font.PLAIN, 17); // fallback
        }

        // ----- WINDOW SETTINGS -----
        setTitle("InventoTrack – Inventory Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // ----- GRADIENT BACKGROUND PANEL -----
        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(150, 200, 255),
                        0, getHeight(), new Color(60, 120, 255)
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bgPanel.setLayout(null);
        setContentPane(bgPanel);

        // ----- GLASS PANEL -----
        JPanel glass = new JPanel();
        glass.setBounds(120, 80, 650, 420);
        glass.setBackground(new Color(255, 255, 255, 80));
        glass.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 150), 2));
        glass.setLayout(null);

        bgPanel.add(glass);

        // ----- TABLE -----
        String[] cols = {"ID", "Name", "Price", "Qty"};
        model = new DefaultTableModel(cols, 0);

        JTable table = new JTable(model);
        table.setFont(montserrat.deriveFont(15f));
        table.setRowHeight(28);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 610, 220);
        sp.setOpaque(false);
        sp.getViewport().setOpaque(false);
        glass.add(sp);

        // ----- BUTTONS -----
        JButton addBtn = neonButton("Add Product");
        JButton updateBtn = neonButton("Update");
        JButton deleteBtn = neonButton("Delete");
        JButton exitBtn = neonButton("Exit");

        addBtn.setBounds(40, 270, 250, 45);
        updateBtn.setBounds(350, 270, 250, 45);
        deleteBtn.setBounds(40, 330, 250, 45);
        exitBtn.setBounds(350, 330, 250, 45);

        glass.add(addBtn);
        glass.add(updateBtn);
        glass.add(deleteBtn);
        glass.add(exitBtn);

        // ----- ACTIONS -----

        addBtn.addActionListener(e -> {
            try {
                String id = JOptionPane.showInputDialog("Enter Product ID:");
                String name = JOptionPane.showInputDialog("Enter Product Name:");
                String price = JOptionPane.showInputDialog("Enter Product Price:");
                String qty = JOptionPane.showInputDialog("Enter Product Quantity:");

                model.addRow(new Object[]{id, name, price, qty});
            } catch (Exception ignored) {}
        });

        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a row first!");
                return;
            }
            String qty = JOptionPane.showInputDialog("Enter New Qty:");
            model.setValueAt(qty, row, 3);
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) model.removeRow(row);
        });

        exitBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    // ----- NEON BUTTON -----
    public JButton neonButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setForeground(Color.BLACK);
        btn.setFont(montserrat);   // *** FONT UPDATED HERE ***
        btn.setBackground(new Color(120, 255, 180));
        btn.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 150), 3));
        btn.setOpaque(true);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(0, 255, 150));
                btn.setForeground(Color.WHITE);
                btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(120, 255, 180));
                btn.setForeground(Color.BLACK);
                btn.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 150), 3));
            }
        });

        return btn;
    }


    public static void main(String[] args) {
        new InventoryGUI();
    }
}
