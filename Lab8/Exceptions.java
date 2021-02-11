class OptionNotRecognizedException extends Exception{
    public OptionNotRecognizedException(String exceptionInfo) {
        System.out.println(exceptionInfo);
    }
}

class WallException extends Exception{
    public WallException(String exceptionInfo) {
        System.out.println(exceptionInfo);
    }
}
