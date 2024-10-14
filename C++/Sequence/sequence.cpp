#include "sequence.h"


/*====================================================================
Function: Sequence -- constructor
Inputs: 
    - size_type sz -- number of elements to initialize
Side Effects: 
    - initialize the doubly linked list
    - set the head and tail pointers to null
    - number of elements in the sequence is already initialized to 0
====================================================================*/

Sequence::Sequence(size_type sz) : head(nullptr), tail(nullptr), numElts(0) {

/*====================================================================
for size type index being initialized to 0, while i is still less than sz, 
i will be incremented after each iteration. After the loop is completed, 
the sequence will contain the elements
====================================================================*/

    for (size_type i = 0; i < sz; i++){
        push_back(0);
    }

}

/*====================================================================
Function: Sequence -- copy constructor
Inputs: 
    - const Sequence& s references an exisiting sequence object tha
will be copied
Side Effects: 
    - initialize the current sequence to a doubly linked list
    - iterates through the nodes of the list and makes a copy
    - each element from s is added to the sequence while maintaining
the order
====================================================================*/

Sequence::Sequence(const Sequence& s) : head(nullptr), tail(nullptr), numElts(0) {

/*====================================================================
while the pointer is on current, the current pointer is initialized
to the head of the sequence and the element in the current node is copied
and the added to the sequence, then current will advance to the next node
====================================================================*/

    SequenceNode* current = s.head;

    while (current) {
        push_back(current->elt);
        current = current->next; 
    }

}

/*====================================================================
Function: Destructor
Inputs: 
    - The deconstructor doesnt take any parameters
Side Effects: 
    - Calls the clear method to remove all nodes from the sequence
====================================================================*/
Sequence::~Sequence() {
    clear();

}

/*====================================================================
Function: Assignment Operator (operator=)
Inputs: 
    - const Sequence& s references to another sequence object that is
being assigned to the current object.
Side Effects: 
    - Memory for the current sequence's nodes is dynamically deallocated
    - The current sequence object is cleared and then filled with the
elements of sequence s.
====================================================================*/

Sequence& Sequence::operator=(const Sequence& s) {

/*====================================================================
If the current object is not the same as object s, clear the sequence,
copy elements and then assign the pointer to s's head node. 

While it is on current, push back current to the element and then move on 
to setting the pointer current to the next node and then return the 
reference to the current object
====================================================================*/

    if (this != &s){
        clear();
        SequenceNode* current = s.head;
        
        while (current){
            push_back(current->elt);
            current = current->next;
        }
    }
    return *this;
}

/*====================================================================
Function: Subscript Operator
Inputs: 
    - size_type position -- the position of the element in the 
sequence must be within the valid range
Side Effects: 
    - This function allows access to an element using array-like syntax
====================================================================*/

Sequence::value_type& Sequence::operator[](size_type position) {

/*====================================================================
If the position of the elements is greater or equal to the number of
elements, throw an exception. 

If an exception is not needed, set the sequence node pointer to 
head and for each elemnet in the sequence, traverse through until
the desired position is reached and return the reference to the element
at the positon
====================================================================*/

    if (position >= numElts) {
        throw exception();
    }

    SequenceNode* current = head;
    for (size_type i = 0; i < position; i++){
        current = current->next;
    }
    return current->elt;
}

/*====================================================================
Function: push_back -- add an element at the end of sequence
Inputs: 
    - const value_type& value -- A reference to the element that needs
to be added to the end of the sequence
Side Effects: 
    - allocates memory for a new node
    - Modifies the head, tail, and next/prev pointers
    - Increments the count of elements 
====================================================================*/

void Sequence::push_back(const value_type& value) {

/*====================================================================
Create a new sequencenode and it will check if the sequence is empty,
it will set the head and tail pointers to the new node as it would 
be the only node in tje sequence. Else, it would link the current
tail node to the new nodee and update the tail pointer, and then
the number of elemnets will be incremented to reflect the addition of
the new number.
====================================================================*/

SequenceNode* newNode = new SequenceNode(value);

    
    if (!head) {
        head = tail = newNode;
    } else {
        tail->next = newNode;
        newNode->prev = tail;
        tail = newNode;
    }
    numElts++;
}

/*====================================================================
Function: pop_back - Remove las element from the sequence
Inputs: 
    - No parameters used
Side Effects: 
    - deallocates memory for the last node
    - Modifies the head, tail, and next/prev pointers
    - Decrements the count of element
====================================================================*/

void Sequence::pop_back() {

/*====================================================================
If there is no tail in the sequence, it will throw an exception. Else, 
create a temporary variable to set the tail node. 

If there is only one element in the sequence, the tail and head are set 
to a null pointer.

Else, there are multiple elements in the sequence, move the tail pointer
to the previous node and then set the new tail's next pointer to null
pointer. Then delete the temporary tail node and decrement the
number of elements
====================================================================*/

    if (!tail) {
        throw exception();
    
    } else {

        SequenceNode* temp = tail;
        if (tail == head) {
            head = tail = nullptr;
        } else {
            tail = tail->prev;
            tail->next = nullptr;
    }

    delete temp;
    numElts --;
    }

}

/*====================================================================
Function: Insert -- inserts an element in a specific place
Inputs: 
    - size_type position -- index at which the new element will
be places
    - value_type value -- value of the element to be inserted
Side Effects: 
    - allocates memory for a new node
    - Modifies the head, tail, and next/prev pointers
    - Increments the count of elements  
====================================================================*/

// insert elemnt specific location
void Sequence::insert(size_type position, value_type value) {

/*====================================================================
If the position is equal to 0, it will throw an exception. If an exception
is not needed, It will then create a new node with the given valu.

If the position == 0 after the new node has been created, it will
then set the pointer to the current head. If it is already at head,
it will update head's pointer. 

Else if the element is being inserted at the end, use puch_ack to handle
the end insertion and the return.

Else, if it is being inserted in the middle of the list, set the current
sequence node to equal head and then traverse through the sequence to 
find the position. and then insert the new node between the current and
previos node and then increment the element count.
====================================================================*/

    if (position == 0){
        throw exception();
    }

    SequenceNode* newNode = new SequenceNode(value);

    if (position == 0) {
        newNode->next = head;
        if (head){
            head->prev = newNode;
        } 
   
   } else if (position == numElts) {
        push_back(value);
        return;
    } else {
        
        SequenceNode* current = head;
        for (size_type i = 0; i < position; i++){
            current = current->next;
        }

        newNode->next = current;
        newNode->prev = current->prev;
        current->prev->next = newNode;
        current->prev = newNode;
    }

    numElts++;
}

/*====================================================================
Function: front -- access the fisrt element of the sequence
Inputs: 
    - No parametrs are used but it is constant
Side Effects: 
    - Access the first element of the sequence
====================================================================*/

const Sequence::value_type& Sequence::front() const {

/*====================================================================
If the sequence is empty, throw an exception

Else, set the element to head and return it
====================================================================*/
    if (empty()){
        throw exception();
    } else {
    return head->elt;
    }
}

/*====================================================================
Function: back -- access the last element of the sequence
Inputs: 
    - No parameters but is constant
Side Effects: 
    - Access the last element of the sequence
====================================================================*/

const Sequence::value_type& Sequence::back() const{
    
/*====================================================================
If the sequence is empty, throw an exception

Else, set the element to tail and return it
====================================================================*/
    if (empty()){
        throw exception();
   
    } else {
        return tail->elt;
    }
}

/*====================================================================
Function: empty -- checks to see if the sequence is empty
Inputs: 
    - No parameters, is just constant
Side Effects: 
    - If the sequence is empty it will return true
    - If the sequence is not empty, return false
====================================================================*/

bool Sequence::empty() const {
    if (numElts > 0){
        return false;
    } else {
        return true;
    }
}

/*====================================================================
Function: size -- get the size of the sequence
Inputs: 
    - No parameters needed
Side Effects: 
    - return the number of elements that are currently in the sequence
====================================================================*/

Sequence::size_type Sequence::size() const {
    return numElts;
}

/*====================================================================
Function: Clear -- clear the entire sequence
Inputs: 
    - No parameters are used or returned
Side Effects: 
    -  When needed, the sequence will be cleared one element at a time
====================================================================*/

void Sequence::clear() {

/*====================================================================
While the sequence is not empty, keep pushing back (removing elements)
until it is completely clear
====================================================================*/
    while(!empty()){
        pop_back();
    }
}

/*====================================================================
Function: Erase -- erase at specific location
Inputs: 
    - size_type position -- index of the element to be removed
Side Effects: 
    - deallocates memory for the removed node
    - Modifies the head, tail, and next/prev pointers
    - Increments the count of elements 
====================================================================*/

void Sequence::erase(size_type position) {

/*====================================================================
If the position is greater than or equal to the number of elements,
throw an exception.

Set the current node to the head and then traverse the target position
starting at the head. 

If the current is already equal to the head, update the head and 
If the list is not empty, set prev to null pointer.

Else if the node to be removed is the tail, set the tail pointer to 
the previos node and set the new tail to null

Else if the node to be removed is in the middle, link previous node to 
next node and then link next node to previous node.

Then deallocate memory for the node and secrement the element count
====================================================================*/
    if (position >= numElts){
        throw exception();  
    } 

    SequenceNode* current = head;
    for (size_type i = 0; i < position; ++i) {
        current = current->next;
    }

    if (current == head) {
        head = head->next;
        if (head) head->prev = nullptr;
    } else if (current == tail) {
        tail = tail->prev;
        tail->next = nullptr;
    } else {
        current->prev->next = current->next;
        current->next->prev = current->prev;
    }

    delete current;
    numElts--;
}

/*====================================================================
Function: erase -- erase a range of elements from a specific location
Inputs: 
    - 
Side Effects: 
    - size_type position: index of the first element to be removed.
    - size_type count: number of consecutive elements to remove.
====================================================================*/

void Sequence::erase(size_type position, size_type count) {

/*====================================================================
If the position and count exceed the number of elements, throw an 
exception

For the elements in the sequence, traverse through to erase the specified 
amount of elements
====================================================================*/
    if (position + count > numElts) {
        throw exception();  
    } 

    for (size_type i = 0; i < count; ++i) {
        erase(position); 
    }
}

/*====================================================================
Function: print -- outputs elements of the sequence
Inputs: 
    - ostream os -- output stream to which the sequence is printed
Side Effects: 
    - prints the elements of the sequence to the provided output stream
    - elements are printed in the format <elt1, elt2,..., entN>
====================================================================*/

void Sequence::print(ostream& os) const {
    os << "<";
    SequenceNode* current = head;
    while(current) {
        os << current->elt;
        if (current->next) os << ", ";
        current = current->next;

    }
    os << ">";
}

/*====================================================================
Function: operator << -- overloads the insertion operator for Sequence
Inputs: 
    - ostream& os --  output stream where the sequence will be printed.
    - const Sequence& s --  sequence to be printed.
Side Effects: 
    - calls the `print()` member function to output the elements of the sequence 
    in the correct format.
    - After printing, it returns the output stream `os` to allow for operator chaining.

====================================================================*/

ostream& operator<<(ostream& os, const Sequence& s) {
    s.print(os);
    return os;
}

