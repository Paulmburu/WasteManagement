package github.paulmburu.domain.usercases.base

interface FlowBaseUseCase<in Unit, out T> {
    operator fun invoke(): T
}