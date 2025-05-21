# Graph-analysis
This project is a program for maintaining and performing operations on graphs.

Each graph contains the following information:
Number: The graph number is a 2-digit number and no two graphs have the same number.
Vertices: The following information is stored for each vertex: The vertex number, which is an 8-digit number and no two vertices have the same number. The vertex weight, which is a real number.
Edges: The following information is stored for each edge: The starting vertex and the ending vertex of the edge, which also determine the direction of the edge. The edge weight, which is a real number.
In the first stage of the project, you must maintain the information for each graph in a suitable format and be able to perform operations of adding, editing, and deleting vertices and edges for each graph. The use of any ready-made data structure in programming languages ​​is allowed.

Note. You can use any of the programming languages ​​Java, C++, C, and Python to complete this project. Using AI tools to complete a project, sharing your programs with others, and using someone else's program is prohibited and constitutes cheating.

Operations performed by the program:

Command: Create an empty graph
By viewing a command in the form
NEW_GRAPH [GRAPH_ID]
In the console, the program creates an empty graph with the number [GRAPH_ID] and saves it.

Command: Add a vertex to a graph
By viewing a command in the form
ADD_VERTEX [GRAPH_ID] [VERTEX_ID] [WEIGHT]
In the console, the program creates a vertex with the number [VERTEX_ID] in the graph that has the number [GRAPH_ID] and sets its weight to [WEIGHT].

Command: Add an edge to a graph
By viewing a command of the form
ADD_EDGE [GRAPH_ID] [START_VERTEX_ID] [END_VERTEX_ID] [WEIGHT]
In the console, the program creates a directed edge from the vertex with the number [START_VERTEX_ID] to the vertex with the number [END_VERTEX_ID] in the graph with the number [GRAPH_ID] and sets its weight to [WEIGHT].

Command: Delete a vertex from a graph
By viewing a command of the form
DEL_VERTEX [GRAPH_ID] [VERTEX_ID]
In the console, the program finds the vertex with the number [VERTEX_ID] in the graph with the number [GRAPH_ID] and deletes it. Note that by deleting a vertex, all its adjacent edges are also deleted from the graph.

Command: Delete Edge from Graph
By viewing a command in the form
DEL_EDGE [GRAPH_ID] [START_VERTEX_ID] [END_VERTEX_ID]
In the console, the program finds the edge from the vertex with the number [START_VERTEX_ID] to the vertex with the number [END_VERTEX_ID] in the graph with the number [GRAPH_ID] and deletes it.

Command: Edit Vertex Weight
By viewing a command in the form
EDIT_VERTEX [GRAPH_ID] [VERTEX_ID] [WEIGHT]
In the console, the program finds the vertex with the number [VERTEX_ID] in the graph with the number [GRAPH_ID] and sets its weight to [WEIGHT].

Command: Edit Edge Weight
By viewing a command in the form
EDIT_EDGE [GRAPH_ID] [START_VERTEX_ID] [END_VERTEX_ID] [WEIGHT]
In the console, the program finds the edge from the vertex with the number [START_VERTEX_ID] to the vertex with the number [END_VERTEX_ID] in the graph with the number [GRAPH_ID] and sets its weight to [WEIGHT].

Query: Show Graph
By viewing a command in the form
SHOW_GRAPH [GRAPH_ID]
in the console, the program finds the graph with the number [GRAPH_ID] and prints its information in the console as follows:
In the first line, the graph number, the number of vertices, and the number of edges are printed:
[GRAPH_ID] [N] [M]
If the number of vertices in the graph is n, in the next n lines, the information for each vertex of the graph is printed in a separate line in the console as follows:
[GRAPH_ID] [VERTEX_ID] [WEIGHT]
If the number of edges in the graph is m, in the next m lines, the information for each vertex of the graph is printed in a separate line in the console as follows:
[GRAPH_ID] [START_VERTEX_ID] [END_VERTEX_ID] [WEIGHT]

Note. The order of outputting the vertices is such that the vertices are sorted by the values ​​of [VERTEX_ID].
Note. The order of outputting the edges should be such that the edges are sorted first by [START_VERTEX_ID] values ​​and then by [END_VERTEX_ID] values.
Note. Print the [WEIGHT] values ​​with 6 decimal places.

Error: Illegal operation
If at any point in the program execution, an illegal command or query is given, the program ignores this command or query and continues its work, and only prints the following message in the console:
INVALID COMMAND

Input
In the first line of input, the number
1≤q≤10^6
is given to you, meaning the number of commands that will be seen in the future. In each of the next q lines, one of the defined commands will come.
Output
The program output is completely consistent with the description given in the definition of the program commands or queries.
