
import java.util.*;
import java.lang.*;

public class BinaryHeap{

    //#region data members

    /**
     * create data members that will allows the class to store data
     * var heapArray - is the array list we will be working on that will have all the data to be altered
     * var heapsize - will allow us to set the size of an array before we even start filling the array data
     */
    private int[] heapArray;
    private int heapSize;

    //#endregion

    //#region constructor

    /**
     * start with an empty contructor - this will allows the us to instatiate the class object with out specifying the array size
     * in the second constructor - will allow us to specify the heap size so not to overload the heap with more that required data during inserting
     */
    public BinaryHeap(){
    }

    public BinaryHeap(int heapArraySize){
        heapSize = 0;
        heapArray = new int[heapArraySize + 1];
        Arrays.fill(heapArray, -1);

    }
    //#endregion


    //#region methods

    /**
     * this will check if the heap is empty or some data occupy the array
     * @return boolean
     */
    public boolean checkIfHeapArrayIsEmpty(){
        return this.heapSize == 0;
    }

    /**
     * this allows us to check if the heap is full given the initial capacity of the heap
     * @return boolean
     */
    public boolean checkIfHeapArrayIsFull(){
        return this.heapSize == heapArray.length;
    }

    private void interchangeNodes(int pos1, int pos2){
        int temp;
        temp = heapArray[pos1];
        heapArray[pos1] = heapArray[pos2];
        heapArray[pos2] = temp;
    }
    /**
     * builds a max heap based on the index given
     * @param position
     */
    private void buildMaxHeap(int position){
        
        if ( heapArray[position] < heapArray[leftChild(position)] || heapArray[position] < heapArray[rightChild(position)] ){

            if ( heapArray[leftChild(position)] > heapArray[rightChild(position)] ){
                interchangeNodes(position, leftChild(position));
                Heapify(leftChild(position));
            }
            else{
                interchangeNodes(position, rightChild(position));
                Heapify(rightChild(position));
            }
        }
    }
    /**
     * returning the position of the parent
     * @param data
     * @return pos of the parent
     */
    private int init(int data){
        return (data-1)/2;
    }

    private void Heapify(int data){
        int temp = heapArray[data];
        while (data > 0 && temp > heapArray[init(data)]){
            heapArray[data] = heapArray[init(data)];
            data = init(data);
        }
        heapArray[data] = temp;
    }
    /**
     * this  method will return the maximum data in the heap
     * @return max
     */
    public int returnMax(){
        if (checkIfHeapArrayIsEmpty()){
            throw new NoSuchElementException("The heap you are accessing is currently empty");
        }
        return heapArray[0];
    }

    public int extractMax(){
        int max = heapArray[1];
        heapArray[1] = heapArray[heapSize --];
        Heapify(1);
        return max;
    }

    private void insert(int data){
        if (checkIfHeapArrayIsFull()){
            throw new NoSuchElementException("The current heap array is Full, unable to process insertion");
        }
        heapArray[heapSize++] = data;
        Heapify(heapSize - 1);
    }

    private int leftChild(int position){
        return (2 * position);
    }

    private int rightChild(int position){
        return (2 * position) + 1;
    }
    //#endregion
}