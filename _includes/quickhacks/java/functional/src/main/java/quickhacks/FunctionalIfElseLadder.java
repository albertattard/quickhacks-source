package quickhacks;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Supplier;

public class FunctionalIfElseLadder<T> {

    private final List<PredicateSupplierPair<T>> options;

    public static <E> FunctionalIfElseLadder<E> ifTrue(final BooleanSupplier predicate,
                                                       final Supplier<E> supplier) {
        requireNonNull(predicate);
        requireNonNull(supplier);

        return new FunctionalIfElseLadder<>(List.of(PredicateSupplierPair.of(predicate, supplier)));
    }

    public FunctionalIfElseLadder<T> elseIf(final BooleanSupplier predicate, final Supplier<T> supplier) {
        requireNonNull(predicate);
        requireNonNull(supplier);

        final List<PredicateSupplierPair<T>> list = new ArrayList<>(options);
        list.add(PredicateSupplierPair.of(predicate, supplier));
        return new FunctionalIfElseLadder<>(List.copyOf(list));
    }

    private FunctionalIfElseLadder(final List<PredicateSupplierPair<T>> options) {
        this.options = options;
    }

    public T orElse(final Supplier<T> supplier) {
        requireNonNull(supplier);

        return asOptional()
                .orElseGet(supplier);
    }

    public <R> Optional<R> map(final Function<T, R> mapper) {
        requireNonNull(mapper);

        return asOptional()
                .map(mapper);
    }

    public <R> R map(final Function<T, R> mapper, final Supplier<R> supplier) {
        requireNonNull(mapper);
        requireNonNull(supplier);

        return asOptional()
                .map(mapper)
                .orElseGet(supplier);
    }

    public Optional<T> asOptional() {
        return options.stream()
                .filter(PredicateSupplierPair::isTrue)
                .findFirst()
                .map(PredicateSupplierPair::value);
    }

    private static class PredicateSupplierPair<T> {
        private final BooleanSupplier predicate;
        private final Supplier<T> supplier;

        public static <E> PredicateSupplierPair<E> of(final BooleanSupplier predicate, final Supplier<E> supplier) {
            requireNonNull(predicate);
            requireNonNull(supplier);
            return new PredicateSupplierPair<>(predicate, supplier);
        }

        private PredicateSupplierPair(final BooleanSupplier predicate, final Supplier<T> supplier) {
            this.predicate = predicate;
            this.supplier = supplier;
        }

        public boolean isTrue() {
            return predicate.getAsBoolean();
        }

        public T value() {
            return supplier.get();
        }
    }
}
