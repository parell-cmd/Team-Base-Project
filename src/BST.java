public class BST {
    private class Node {
        Patient patient;
        Node left, right;
        Node(Patient p) { this.patient = p; }
    }

    private Node root;

    public void insertPatient(Patient p) {
        root = insert(root, p);
    }

    private Node insert(Node node, Patient p) {
        if (node == null) return new Node(p);
        if (p.id < node.patient.id) node.left = insert(node.left, p);
        else if (p.id > node.patient.id) node.right = insert(node.right, p);
        return node;
    }

    public Patient searchPatient(int id) {
        Node curr = root;
        while (curr != null) {
            if (id == curr.patient.id) return curr.patient;
            else if (id < curr.patient.id) curr = curr.left;
            else curr = curr.right;
        }
        return null;
    }

    public void inOrderDisplay() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.patient);
        inOrder(node.right);
    }
}

