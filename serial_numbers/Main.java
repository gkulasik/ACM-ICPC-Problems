package f_serial_numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;


//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1941372 for the problem 4172 - Serial Numbers has succeeded with verdict Accepted.
//
//Congratulations! Now it is time to try a new problem.
//
//Best regards,
//
//The ACM-ICPC Live Archive team


class Main {

	public static PriorityQueue<Entry> q;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String current = input.nextLine();
		while(!current.trim().equals("END")){
			q = new PriorityQueue<>();
			String header = current;
			current = input.nextLine();
			while(!current.trim().equals("0")){
				String [] entry = current.split(" ");
				int start = Integer.parseInt(entry[0]);
				int end = Integer.parseInt(entry[1]);
				int code = Integer.parseInt(entry[3]);
				createEntry(new Entry(start, end, entry[2], code));
				current = input.nextLine();
			}
			List<Entry> toSort = Arrays.asList(q.toArray(new Entry[q.size()]));
			Collections.sort(toSort);
			q.clear();
			q.addAll(toSort);
			combineConsecutive();
			printTable(header);
			current = input.nextLine();
		}
	}
	
	private static void printTable(String header){
		System.out.println(header);
		for(Entry entry : q){
			System.out.println(entry.toString());
		}
	}
	private static void createEntry(Entry toAdd){
		PriorityQueue<Entry> affected = getAffectedEntries(toAdd);
		q.removeAll(affected);
		PriorityQueue<Entry> resolved = new PriorityQueue<>();
		for(Entry entry : affected){
			resolved.addAll(adjustEntry(entry, toAdd));
			removeDups(resolved);
		}
		if(affected.isEmpty()) resolved.add(toAdd); //  for first entry
		q.addAll(resolved);
	}
	
	private static PriorityQueue<Entry> adjustEntry(Entry old, Entry toAdd){
		PriorityQueue<Entry> adjustment = new PriorityQueue<>();
		if(old.start == toAdd.start && old.end == toAdd.end){
			adjustment.add(toAdd);
			return adjustment;
		}
		if(old.start == toAdd.start && old.end > toAdd.end){
			Entry filler = new Entry(toAdd.end+1, old.end, old.status, old.code);
			adjustment.add(toAdd);
			adjustment.add(filler);
			return adjustment;
		}
		if(old.start < toAdd.start && old.end == toAdd.end){
			old.end = toAdd.start-1;
			adjustment.add(old);
			adjustment.add(toAdd);
			return adjustment;
		}
		if(old.start < toAdd.start && old.end > toAdd.end){
			Entry filler = new Entry(toAdd.end+1, old.end, old.status, old.code);
			old.end = toAdd.start-1;
			adjustment.add(old);
			adjustment.add(toAdd);
			adjustment.add(filler);
			return adjustment;
		}
		if(old.start >= toAdd.start && old.end <= toAdd.end){
			adjustment.add(toAdd);
			// old is deleted essentially
			return adjustment;
		}
		if(old.start <= toAdd.start && old.end <= toAdd.end && toAdd.start <= old.end){
			old.end = toAdd.start-1;
			adjustment.add(old);
			adjustment.add(toAdd);
			return adjustment;
		}
		if(old.start >= toAdd.start && old.end >= toAdd.end && old.start <= toAdd.end){
			old.start = toAdd.end+1;
			adjustment.add(old);
			adjustment.add(toAdd);
			return adjustment;
		}

		return adjustment;
	}
	
	private static void removeDups(PriorityQueue<Entry> q){
		Entry[] entries = q.toArray(new Entry[q.size()]);
		for(Entry entry : entries){
			q.remove(entry);
			if(q.contains(entry)){
				q.remove(entry);
			}
			q.add(entry);
		}
	}
	
	private static void combineConsecutive(){
		ArrayList<Entry> entries = new ArrayList<>(Arrays.asList(q.toArray(new Entry[q.size()])));
		Collections.sort(entries);
		for(int i = 0; i < entries.size(); i++){
			if(i+1 != entries.size()){
				Entry first = entries.get(i);
				Entry second = entries.get(i+1);
				if((first.end+1 == second.start) && first.code == second.code && first.status.equals(second.status)){
					entries.remove(first);
					entries.remove(second);
					Entry newEntry = new Entry(first.start, second.end, first.status, first.code);
					entries.add(newEntry);
					i = -1;
					Collections.sort(entries);
				}
			}
		}
		q.clear();
		q.addAll(entries);
	}
	
	private static PriorityQueue<Entry> getAffectedEntries(Entry toAdd){
		PriorityQueue<Entry> affected = new PriorityQueue<>();
		for(Entry entry : q){
			boolean affect = false;
			if(entry.start <= toAdd.start && entry.end >= toAdd.end) affect = true; // inside
			if(entry.start >= toAdd.start && entry.end <= toAdd.end) affect = true; // envelope
			if(entry.start <= toAdd.start && entry.end <= toAdd.end && toAdd.start <= entry.end) affect = true; // right overlap
			if(entry.start >= toAdd.start && entry.end >= toAdd.end && entry.start <= toAdd.end) affect = true; // left overlap
			if(affect) affected.add(entry);
		}
		return affected;
	}
}

class Entry implements Comparable{
	public int start;
	public int end;
	public String status;
	public int code;
	
	Entry(int start, int end, String status, int code){
		this.start = start;
		this.end = end;
		this.status = status;
		this.code = code;
	}

	@Override
	public int compareTo(Object o) {
		if(o == null) return 1;
		if(!(o instanceof Entry)) return 1;
		Entry that = (Entry) o;
		if(that.start > this.start) return -1;
		if(that.start < this.start) return 1;
		return 0;
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null) return false;
		if(!(o instanceof Entry)) return false;
		Entry that = (Entry) o;
		if(that.start != this.start) return false;
		if(that.end != this.end) return false;
		if(that.code != this.code) return false;
		if(!that.status.equals(this.status)) return false;
		return true;
	}
	
	@Override
	public String toString(){
		return start + " " + end + " " + status + " " + code;
	}
	
}