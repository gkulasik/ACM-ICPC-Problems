package electronic_document_security;

//Hi,
//
//This is an automated response from ACM-ICPC Live Archive.
//
//Your submission with number 1936871 for the problem 3851 - Electronic Document Security has succeeded with verdict Accepted.
//
//Congratulations! Now it is time to try a new problem.
//
//Best regards,
//
//The ACM-ICPC Live Archive team



import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {
		int counter = 1;
		Scanner input = new Scanner(System.in);
		TreeMap<String, LinkedList<String>> ACL = new TreeMap<>();
		String current = input.nextLine();
		while(!current.trim().equals("#")){
			String[] logs = current.split(",");
			for(String log : logs){
				if(log.contains("-")){
					String[] parts = log.split("-");
					remove(ACL, parts[0], parts[1]);
				}
				if(log.contains("+")){
					String[] parts = log.split("\\+");
					add(ACL, parts[0], parts[1]);
				}
				if(log.contains("=")){
					String[] parts = log.split("=");
					equ(ACL, parts[0], parts[1]);
				}
			}
			System.out.println(print(counter, ACL));
			counter++;
			current = input.nextLine();
			ACL = new TreeMap<>();
		}
	}
	
	private static void remove(TreeMap<String, LinkedList<String>> acl, String rights, String entitys){	
		for(int i = 0; i < rights.length(); i++){
			for(int j = 0; j < entitys.length(); j++){
				String right = ""+rights.charAt(i);
				String entity = ""+entitys.charAt(j);
				LinkedList<String> entries = acl.get(right);
				if(entries != null){
					entries.remove(entity);
				}
			}
		}
	}
	
	private static void add(TreeMap<String, LinkedList<String>> acl, String rights, String entitys){
		for(int i = 0; i < rights.length(); i++){
			for(int j = 0; j < entitys.length(); j++){
				String right = ""+rights.charAt(i);
				String entity = ""+entitys.charAt(j);
				LinkedList<String> entries = acl.get(right);
				if(entries != null){
					if(!entries.contains(entity))
						entries.add(entity); // remove redundancy
				}
				else
				{
					acl.put(right, new LinkedList<String>());
					add(acl, right, entity);
				}
			}
		}
	}
	
	private static void equ(TreeMap<String, LinkedList<String>> acl, String rights, String entitys){
		for(int i = 0; i < rights.length(); i++){
			String right = ""+rights.charAt(i);
			if(acl.get(right) != null) acl.get(right).clear();
			for(int j = 0; j < entitys.length(); j++){
				String entity = ""+entitys.charAt(j);
				add(acl, right, entity);
			}
		}
	}
	
	
	private static String print(int count, TreeMap<String, LinkedList<String>> acl){
		TreeMap<String, String> merged = new TreeMap<>();
		StringBuilder result = new StringBuilder();
		for(String key : acl.keySet()){
			if(!acl.get(key).isEmpty()){
				Collections.sort(acl.get(key));
				for(String entity : acl.get(key)){
					result.append(entity);
				}
				merged.put(key, result.toString());
			}
			result = new StringBuilder();
		}
		merged = merge(merged);
		result.append(count + ":");
		for(String key : merged.keySet()){
				result.append(key+merged.get(key));
		}
		return result.toString();
	}
	
	private static TreeMap<String, String> merge(TreeMap<String, String> toMerge){
		//<S, c>
		// <P, aw>, <L, aw>
		// <P, aw>, <L, aw>, <K, aw>
		TreeMap<String, String> result = new TreeMap<>();
		TreeMap<String, String> toCombine = new TreeMap<>();
		TreeMap<String, String> toDelete = new TreeMap<>();
		String[] keys = toMerge.keySet().toArray(new String[toMerge.keySet().size()]);
		for(int i = 0; i < keys.length; i++){
			String key = keys[i];
			consecutive: for(int j = i; j < keys.length; j++){
				String key2 = keys[j];
				if(key.compareTo(key2) < 0 && toMerge.get(key).equals(toMerge.get(key2))){
					toCombine.put(key, toMerge.get(key));
					toCombine.put(key2,  toMerge.get(key2));
					toDelete.put(key, "");
					toDelete.put(key2,  "");
					i = j-1;
				}
				else{
					if(key.compareTo(key2) != 0){
						i = j-1;
						break consecutive;
					}

				}
			}
			if(!toCombine.isEmpty()){
				String newKey = "", newEntry = "";
				String currentKey = "";
				for(String combiner : toCombine.keySet()){
					currentKey = combiner;
					if(!newKey.contains(combiner))
						newKey += combiner;
				}
				newEntry += toCombine.get(currentKey);
				result.put(newKey, newEntry);
				toCombine.clear();
			}
		}
		for(String delete : toDelete.keySet()){
			toMerge.remove(delete);
		}
		for(String add : toMerge.keySet()){
			result.put(add,toMerge.get(add));
		}
		return result;
		
	}

}
