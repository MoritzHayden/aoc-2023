package dev.hmoritz.aoc2023.util

class BinaryTreeNode<T>(
    private val value: T,
    private var parent: BinaryTreeNode<T>? = null,
    private var left: BinaryTreeNode<T>? = null,
    private var right: BinaryTreeNode<T>? = null
) {
    fun getValue(): T = this.value

    fun getRoot(): BinaryTreeNode<T> {
        var root = this
        while (root.parent != null) root = root.parent!!
        return root
    }

    fun getParent(): BinaryTreeNode<T>? = this.parent

    fun getLeft(): BinaryTreeNode<T>? = this.left

    fun getRight(): BinaryTreeNode<T>? = this.right

    fun addLeft(value: T) {
        this.left = BinaryTreeNode(value)
    }

    fun addRight(value: T) {
        this.right = BinaryTreeNode(value)
    }

    fun find(value: T): BinaryTreeNode<T>? =
        if (this.value == value) this else left?.find(value) ?: right?.find(value)

    fun traverseInOrder(): List<T> {
        val list = mutableListOf<T>()
        left?.traverseInOrder()?.let { list.addAll(it) }
        list.add(value)
        right?.traverseInOrder()?.let { list.addAll(it) }
        return list
    }

    fun traversePreOrder(): List<T> {
        val list = mutableListOf<T>()
        list.add(value)
        left?.traversePreOrder()?.let { list.addAll(it) }
        right?.traversePreOrder()?.let { list.addAll(it) }
        return list
    }

    fun traversePostOrder(): List<T> {
        val list = mutableListOf<T>()
        left?.traversePostOrder()?.let { list.addAll(it) }
        right?.traversePostOrder()?.let { list.addAll(it) }
        list.add(value)
        return list
    }

    override fun toString(): String {
        return "BinaryTreeNode(value=$value, parent=${parent?.value}, left=${left?.value}, right=${right?.value})"
    }
}
