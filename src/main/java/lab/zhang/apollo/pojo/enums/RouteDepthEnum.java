package lab.zhang.apollo.pojo.enums;

public enum RouteDepthEnum {
    /**
     * no recursive
     */
    NONE,

    /**
     * recursive one depth
     */
    ONCE,

    /**
     * recursive to the end
     */
    TO_THE_END,
    ;

    static public RouteDepthEnum dec(RouteDepthEnum originDepth) {
        switch (originDepth) {
            case ONCE:
                return NONE;
            case TO_THE_END:
                return TO_THE_END;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
