import networkx as nx
import matplotlib.pyplot as plt
import heapq

def dijkstra(G, source, pos):
    # Initialization: distances and previous vertices
    dist = {vertex: float('infinity') for vertex in G}
    previous = {vertex: None for vertex in G}
    dist[source] = 0
    pq = [(0, source)]
    
    while pq:
        current_distance, current_vertex = heapq.heappop(pq)

        if current_distance > dist[current_vertex]:
            continue

        for neighbor in G.neighbors(current_vertex):
            distance = G.edges[current_vertex, neighbor]['weight']
            new_distance = current_distance + distance
            if new_distance < dist[neighbor]:
                dist[neighbor] = new_distance
                previous[neighbor] = current_vertex
                heapq.heappush(pq, (new_distance, neighbor))
        
        # Visualize the step
        nx.draw(G, pos, with_labels=True, node_color='lightblue')
        nx.draw_networkx_edges(G, pos, edgelist=[(previous[n], n) for n in G if previous[n]], edge_color='red')
        plt.show()

def bellman_ford(G, source, pos):
    # Step 1: Initialize graph
    dist = {vertex: float('infinity') for vertex in G}
    dist[source] = 0
    previous = {vertex: None for vertex in G}

    # Step 2: Relax edges repeatedly
    for _ in range(len(G) - 1):
        for u, v, data in G.edges(data=True):
            weight = data['weight']
            if dist[u] + weight < dist[v]:
                dist[v] = dist[u] + weight
                previous[v] = u

                # Visualize the step
                nx.draw(G, pos, with_labels=True, node_color='lightblue')
                nx.draw_networkx_edges(G, pos, edgelist=[(previous[n], n) for n in G if previous[n]], edge_color='green')
                plt.show()

    # Step 3: Check for negative-weight cycles
    for u, v, data in G.edges(data=True):
        weight = data['weight']
        if dist[u] + weight < dist[v]:
            print("Graph contains a negative weight cycle")
            return

# Example usage
G = nx.DiGraph()
edges = [('A', 'B', 5), ('B', 'C', 2), ('A', 'C', 10), ('C', 'D', 1), ('B', 'D', 4)]
G.add_weighted_edges_from(edges)

pos = nx.spring_layout(G)  # positions for all nodes
plt.figure(figsize=(12, 12))
plt.title('Dijkstra\'s Algorithm Visualization')
dijkstra(G, 'A', pos)

plt.figure(figsize=(12, 12))
plt.title('Bellman-Ford Algorithm Visualization')
bellman_ford(G, 'D', pos)
