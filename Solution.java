import java.util.*;

public class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        // Step 1: Count Frequencies
        // Create a map to store the frequency of each word
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }

        // Step 2: Use a Min-Heap to Keep Top K Frequent Words
        // PriorityQueue with custom comparator:
        // 1. Sort by frequency in ascending order.
        // 2. If frequencies are the same, sort lexicographically in descending order.
        PriorityQueue<String> minHeap = new PriorityQueue<>((word1, word2) -> 
            wordFrequency.get(word1).equals(wordFrequency.get(word2)) ? 
            word2.compareTo(word1) : wordFrequency.get(word1) - wordFrequency.get(word2));

        for (String word : wordFrequency.keySet()) {
            minHeap.offer(word);
            if (minHeap.size() > k) {
                minHeap.poll();  // Remove the word with the smallest frequency
            }
        }

        // Step 3: Extract Results from the Heap
        // Retrieve words from the heap and reverse the list to get the correct order
        List<String> topWords = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            topWords.add(minHeap.poll());
        }
        Collections.reverse(topWords);  // Reverse to get the correct order
        
        return topWords;
    }
}
