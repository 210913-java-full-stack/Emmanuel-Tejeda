
public class MyLinkedList
{

    class Node <T>
    {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }


    public Node head = null;
    public Node tail = null;


    public void addNode(Object data){

        Node<Object> newNode = new Node<Object>(data);

        if (head == null) {

            head = newNode;
            tail = newNode;
        } else {

            tail.next = newNode;

            tail = newNode;
        }
    }

    public void display() {

        Node current = head;

        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        while (current != null) {

            System.out.print(current.data + "\n");
            current = current.next;
        }

    }

    //Get data from a specific node in the linked list
    public Object getData(Object request)
    {
        Node <Object> current = head;

        //if list is empty
        if (head == null)
        {
            System.out.println("List is empty");
            return "List is empty";
        }
        while (current != null)
        {
            //If data found return it
            if(request == current.data)
            {
                System.out.println("The request for: \"" + request + "\" was found");
                return current.data;
            }
            current = current.next;

        }
        //If requested data not found in the list
        System.out.println("Sorry the data you asked for was not found");
        return "";
    }


    void deleteList()
    {
        head = null;
    }



}