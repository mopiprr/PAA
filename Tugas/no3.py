#NO3

class Node:
    def __init__(self, is_internal=True):
        self.is_internal = is_internal
        self.left = None
        self.right = None

def build_extended_binary_tree(internal_nodes):
    # Base case: if no internal nodes, hence an empty tree
    if internal_nodes == 0:
        return Node(False), 1

    # Start with one internal node
    root = Node()
    internal_nodes_left = internal_nodes - 1
    external_nodes_count = 0

    # Queue for level-order tree construction
    queue = [root]
    while queue and internal_nodes_left > 0:
        current = queue.pop(0)

        # Add left child
        if internal_nodes_left > 0:
            current.left = Node()
            internal_nodes_left -= 1
            queue.append(current.left)
        else:
            current.left = Node(False)
            external_nodes_count += 1

        # Add right child
        if internal_nodes_left > 0:
            current.right = Node()
            internal_nodes_left -= 1
            queue.append(current.right)
        else:
            current.right = Node(False)
            external_nodes_count += 1

    # Adding external nodes for the remaining queue if children are not set
    for node in queue:
        if node.left is None:
            node.left = Node(False)
            external_nodes_count += 1
        if node.right is None:
            node.right = Node(False)
            external_nodes_count += 1

    return root, external_nodes_count

# Function to count the external nodes
def count_external_nodes(root):
    if root is None:
        return 0
    if not root.is_internal:
        return 1
    return count_external_nodes(root.left) + count_external_nodes(root.right)

# Example:
internal_nodes = 5
root, external_nodes_count = build_extended_binary_tree(internal_nodes)
print("Internal nodes declared:", internal_nodes)
print("Calculated external nodes:", external_nodes_count)
print("Expected external nodes:", internal_nodes + 1)
