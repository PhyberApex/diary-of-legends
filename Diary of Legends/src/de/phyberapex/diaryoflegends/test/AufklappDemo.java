package de.phyberapex.diaryoflegends.test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;

public class AufklappDemo extends JFrame implements MouseListener, MouseMotionListener {

    private JToolBar toolbar;
    private JButton btTree;
    private JPanel mainPanel;
    private JScrollPane klappPane;
    private JTree tree;
    private DefaultMutableTreeNode root;
    private JTabbedPane tabbedpane;

    public AufklappDemo() {
        super("AufklappDemo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);
        mainPanel = new JPanel(new BorderLayout());
        root = new DefaultMutableTreeNode("Meine Bilder");
        for (int i = 0; i < 30; i++) {
            DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("Bild " + i);
            root.add(child1);
        }
        tree = new JTree(root);
        klappPane = new JScrollPane();
        klappPane.setPreferredSize(new Dimension(200, 0));
        klappPane.setViewportView(tree);
        if (JOptionPane.showConfirmDialog(this, "Overlap?") == JOptionPane.YES_OPTION) {
            getLayeredPane().add(klappPane, JLayeredPane.POPUP_LAYER);
            klappPane.setBounds(28, 0, 200, getSize().height - 30);
        } else {
            mainPanel.add(klappPane, BorderLayout.WEST);
        }
        tabbedpane = new JTabbedPane();
        mainPanel.add(tabbedpane);
        tabbedpane.addMouseListener(this);
        tree.addMouseMotionListener(this);
        klappPane.addMouseListener(this);
        tree.addMouseListener(this);
        toolbar = new JToolBar();
        toolbar.setOrientation(JToolBar.VERTICAL);
        toolbar.setFloatable(false);
        toolbar.setRollover(true);
        btTree = new JButton();
        btTree.setFocusable(false);
        btTree.setMaximumSize(new Dimension(22, 60));
        Icon graphicIcon = UIManager.getIcon("Tree.closedIcon");
        btTree.setIcon(graphicIcon);
        toolbar.add(btTree);
        toolbar.setPreferredSize(new Dimension(28, 0));
        getContentPane().add(toolbar, BorderLayout.WEST);
        getContentPane().add(mainPanel);
        btTree.addMouseListener(this);
    }

    public void mousePressed(final MouseEvent e) {
        Object source = e.getSource();
        if (source == tree && e.getClickCount() == 2) {
            openSelected();
            return;
        }
    }

    public void mouseEntered(final MouseEvent e) {
        Object source = e.getSource();
        if (source == btTree) {
            klappPane.setVisible(true);
            klappPane.setBounds(28, 0, 200, getSize().height - 30);
        } else if (source == tabbedpane) {
            klappPane.setVisible(false);
        }
        mainPanel.revalidate();
    }

    private void openSelected() {
        DefaultMutableTreeNode selectedNode =
                (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (selectedNode.isLeaf()) {
            openSelectedDocument(selectedNode);
        }
    }

    private void openSelectedDocument(final DefaultMutableTreeNode node) {
        for (int i = 0; i < tabbedpane.getTabCount(); i++) {
            if (tabbedpane.getTitleAt(i).equals(node.toString())) {
                tabbedpane.setSelectedIndex(i);
                tabbedpane.scrollRectToVisible(tabbedpane.getBoundsAt(i));
                return;
            }
        }
        JPanel panel = new JPanel();
        panel.add(new JLabel(node.toString()));
        tabbedpane.addTab(node.toString(), panel);
        tabbedpane.setSelectedComponent(panel);
        tabbedpane.scrollRectToVisible(tabbedpane.getBoundsAt(tabbedpane.getSelectedIndex()));
    }

    public void mouseDragged(final MouseEvent e) {
    }

    public void mouseMoved(final MouseEvent e) {
    }

    public void mouseExited(final MouseEvent e) {
    }

    public void mouseReleased(final MouseEvent e) {
    }

    public void mouseClicked(final MouseEvent e) {
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                new AufklappDemo().setVisible(true);
            }
        });
    }
}