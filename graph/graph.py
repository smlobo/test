#!/usr/bin/env python3.11

import networkx as nx
import matplotlib.pyplot as plt

# Build a simple TensorFlow-style graph: z = x * y + 2
G = nx.DiGraph()

# Add nodes (operations/constants)
G.add_node("x", label="Input: x")
G.add_node("y", label="Input: y")
G.add_node("mul", label="Op: Multiply")
G.add_node("const2", label="Const: 2")
G.add_node("add", label="Op: Add")
G.add_node("z", label="Output: z")

# Add edges (data flow)
G.add_edge("x", "mul")
G.add_edge("y", "mul")
G.add_edge("mul", "add")
G.add_edge("const2", "add")
G.add_edge("add", "z")

# Draw
pos = {
    "x": (-2, 1),
    "y": (-2, -1),
    "mul": (0, 0),
    "const2": (0, -2),
    "add": (2, 0),
    "z": (4, 0)
}

labels = nx.get_node_attributes(G, 'label')
plt.figure(figsize=(8,5))
nx.draw(G, pos, with_labels=True, labels=labels, node_size=3000, node_color="lightblue", font_size=9, arrows=True)
plt.title("TensorFlow Graph: z = x * y + 2")
plt.show()
