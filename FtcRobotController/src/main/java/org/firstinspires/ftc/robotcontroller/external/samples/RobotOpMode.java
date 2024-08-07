public interface RobotOpMode {
    void initialize();
    void execute();
    default void start() {}
    default void stop() {}
}

