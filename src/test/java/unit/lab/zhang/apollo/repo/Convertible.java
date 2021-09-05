package unit.lab.zhang.apollo.repo;

/**
 * @author zhangrj
 */
public interface Convertible<P, R> {
    R covertFrom(P param);
}
