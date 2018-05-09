package multithreading.examples.threelists.usingcyclicbarrier;

class Entry implements Comparable{
    private final Integer entry;
    private final Integer threadId;

    public Entry(Integer entry, Integer threadId) {
        this.entry = entry;
        this.threadId = threadId;
    }

    @Override
    public int compareTo(Object o) {
        return threadId.compareTo(((Entry)o).threadId);
    }

    @Override
    public String toString() {
        return "Thread " + threadId +
                " printed =" + entry;
    }
}
