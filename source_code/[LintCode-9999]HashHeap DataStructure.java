/***HashHeap***/
class HashHeap {
    // inner class
    class Node {
        // fields
        public Integer position;
        public Integer count;		
        // constructor-1
        Node(Node now) {
            position = now.position;
            count = now.count;
        }		
        // constructor-2
        Node(Integer first, Integer second) {
            this.position = first;
            this.count = second;
        }
    }
	
    // fields
    List<Integer> list;
    String mode;// mode = {"min(Heap)", "max(Heap)"}
    int size;
    HashMap<Integer, Node> map;

    // constructor
    public HashHeap(String mod) {
        list = new ArrayList<Integer>();
        mode = mod;
        map = new HashMap<Integer, Node>();
        size = 0;
    }

    // helper methods
    int getParent(int position) {
        if (position == 0) {
            return -1;
        }

        return (position - 1) / 2;
    }

    int getLeftSon(int position) {
        return position * 2 + 1;
    }

    int getRightSon(int position) {
        return position * 2 + 2;
    }

    boolean compareSmall(int a, int b) {
        if (a <= b) {
            if (mode == "min") {
                return true;
            }
            else {
                return false;
            }
        } else {
            if (mode == "min") {
                return false;
            }
            else {
                return true;
            }
        }
    }

    void swap(int positionA, int positionB) {
        int valA = list.get(positionA);
        int valB = list.get(positionB);

        int countA = map.get(valA).count;
        int countB = map.get(valB).count;

        map.put(valB, new Node(positionA, countB));
        map.put(valA, new Node(positionB, countA));
        list.set(positionA, valB);
        list.set(positionB, valA);
    }

    void siftup(int position) {
        while (getParent(position) > -1) {
            int parentPos = getParent(position);
            if (compareSmall(list.get(parentPos), list.get(position)) == true) {
                break;
            } else {
                swap(position, parentPos);
            }
            position = parentPos;
        }
    }

    void siftdown(int position) {
        while (getLeftSon(position) < list.size()) {
            int leftChildPos = getLeftSon(position);
            int rightChildPos = getRightSon(position);
            int smaller;
            if (rightChildPos >= list.size()|| 
               (compareSmall(list.get(leftChildPos), list.get(rightChildPos)) == true)){
                smaller = leftChildPos;
            } else {
                smaller = rightChildPos;
            }
            if (compareSmall(list.get(position), list.get(smaller)) == true) {
                break;
            } else {
                swap(position, smaller);
            }
            position = smaller;
        }
    }
	
    // public methods
    public int peek() {
        return list.get(0);
    }

    public int size() {
        return size;
    }

    public Boolean isEmpty() {
        return (list.size() == 0);
    }

    public Integer poll() {
        size--;
        Integer now = list.get(0);
        Node current = map.get(now);
        if (current.count == 1) {
            swap(0, list.size() - 1);
            map.remove(now);
            list.remove(list.size() - 1);
            if (list.size() > 0) {
                siftdown(0);
            }
        } else {
            map.put(now, new Node(0, current.count - 1));
        }
		
        return now;
    }

    public void offer(int now) {
        size++;
        if (map.containsKey(now)) {
            Node current = map.get(now);
            map.put(now, new Node(current.position, current.count + 1));
        } else {
            list.add(now);
            int lastPos = list.size() - 1;
            map.put(now, new Node(lastPos, 1));

            siftup(lastPos);
        }		
    }

    public void remove(int now) {
        size--;
        Node current = map.get(now);
        int currentPosition = current.position;
        int count = current.count;

        if (current.count == 1) {
            int lastPos = list.size() - 1;
            swap(currentPosition, lastPos);
            map.remove(now);
            list.remove(lastPos);
            if (this.size > currentPosition) {
                siftup(id);
                siftdown(id);
            }
        } else {
            map.put(now, new Node(id, count - 1));			
        }
    }
}
