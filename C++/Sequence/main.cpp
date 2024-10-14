/*
Author: Kalli Koppin
Class: CS 3100 Data Structures & Algorithms
Due Date: October 9th, 2024
Summary: This program creates an abstract data type and uses pointers
for a sequence and attempts to try a bunch of tests to edit and print the 
sequence.
*/

#include <iostream>
#include <iomanip>
#include <string>
#include "sequence.h"

using namespace std;

int main(){
// creating a seporator string for separate tests
	string seporator = "--------------------------------------";
	Sequence seq(4);

// creating the sequence and adding elements in the sequence test
	cout << seporator << endl;
	cout << "Adding elements into sequence..." << endl;
	seq[0] = 100;
	seq[1] = 200;
	seq[2] = 300;
	cout << "Elements added\n" << "Sequence: " << seq << endl;
	cout << seporator << endl;
	
// access the first and last element of the sequence test
	cout << "Accessing first element in sequence..." << endl;
	cout << "First: " << seq.front() << endl;
	cout << "Accessing last element in sequence..." << endl;
	cout << "Last: " << seq.back() << endl;
	cout << seporator << endl;

// changing the first element test
	cout << "Current Sequence: " << seq << endl;
	cout << "Changing first element..." << endl;
	seq[0] = 150;
	cout << "New Sequence: " << seq << endl;
	cout << seporator << endl;

// inserting an element in sequence that is not filled
	cout << "Current Sequence: " << seq << endl;
	cout << "Inserting 175 at position 2..." << endl;
	seq.insert(2,175);
	cout << "New Sequence: " << seq << endl;
	cout << seporator << endl;

// removing last element in sequence test
	cout << "Current Sequence: " << seq << endl;
	cout << "Removing last element... " << endl;
	seq.pop_back();
	cout << "New Sequence: " << seq << endl;
	cout << seporator << endl;

// erasing last element in sequence test
	cout << "Current Sequence: " << seq << endl;
	cout << "Erasing element at position one..." << endl;
	seq.erase(1);
	cout << "New Sequence: " << seq << endl;
	cout << seporator << endl;

// clearing the sequence test
	cout << "Current Sequence: " << seq << endl;
	seq.clear();
	cout << "New Sequence: " << seq << endl;
	cout << seporator << endl;
}
