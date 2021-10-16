class PageRank {
    static final int V = 4;
    // Matrix Become Zero
    static void zeroMatrix(float adjMatrix[][]) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                adjMatrix[i][j] = 0;
            }
        }

    }
    // Matrix Creations
    static void AddEdge(float adjMatrix[][], int src, int dst) {
        adjMatrix[src][dst] = 1;
    }
    // Count Common Element in each row and divied the row with them
    static void CountCommonElementMatrix(float adjMatrix[][]) {

        int count = 0;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (adjMatrix[i][j] == 1) {
                    count++;
                }
            }
            for (int j = 0; j < V; j++) {
                try {
                    adjMatrix[i][j] /= count;
                } catch (ArithmeticException e) {
                    adjMatrix[i][j] = 0;
                }
            }
            count = 0;
        }
    }
    static void Calculate(float adjMatrix[][], float tempPR[]) {
        // PR1= adjMatrix * PR0
        String[] webSites = { "Geek For Geeks", "Javatpoint", "Tutorial Point", "Programiz" };
        float[] tempWeb = new float[V];
        float sum = 0;
        
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                sum += (float)adjMatrix[j][i] * tempPR[i];
            }
            tempPR[i] = sum;
            sum = 0;
        }
        System.out.println("Working Calculate");
        for (int i = 0; i < V; i++) {
            System.out.println(" " + tempPR[i]);
        }
        for(int i = 0; i < V; i++) {
            int index = i;
            for(int j = i+1; j < V; j++) {
                if(tempPR[j]>tempPR[index]) {
                    index = j;
                }
            }
            float temp1 = tempPR[index];
            tempPR[index] = tempPR[i];
            tempPR[i] = temp1;

            String temp2 =webSites[index];
            webSites[index] = webSites[i];
            webSites[i] = temp2;

        }

        System.out.println("Most Search Websites: ");
        for(int i = 0; i < V; i++){
            System.out.println((i+1)+" "+webSites[i]);
        }

    }
    public static void main(String[] args) {
        System.out.println("Page Rank Algorithm");

        float[][] adjMatrix = new float[V][V];

        // System.out.println(" "+(float)(1/4));
        // System.out.println(" "+V);
        float[] tempPR = new float[V];
        for (int i = 0; i < V; i++) {
            tempPR[i] = (float)1/V;
        }
        zeroMatrix(adjMatrix);
        // A 
        AddEdge(adjMatrix, 0, 1);
        AddEdge(adjMatrix, 0, 2);
        // B 
        AddEdge(adjMatrix, 1, 0);
        AddEdge(adjMatrix, 1, 3);
        // C
        AddEdge(adjMatrix, 2, 0);
        AddEdge(adjMatrix, 2, 1);
        AddEdge(adjMatrix, 2, 3);
        // D
        AddEdge(adjMatrix, 3, 0);
        CountCommonElementMatrix(adjMatrix);
        Calculate(adjMatrix, tempPR);
        // PrintMatrix(adjMatrix);
    }
}


    // Print Matrix
    // static void PrintMatrix(float adjMatrix[][]) {
    //     for (int i = 0; i < V; i++) {
    //         for (int j = 0; j < V; j++) {
    //             System.out.print("    " + adjMatrix[i][j]);
    //         }
    //         System.out.println();
    //     }
    // }