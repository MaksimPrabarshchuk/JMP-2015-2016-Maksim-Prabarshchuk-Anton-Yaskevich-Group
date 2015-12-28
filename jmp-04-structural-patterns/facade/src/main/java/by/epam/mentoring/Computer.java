package by.epam.mentoring;

class Computer {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public Computer() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void startComputer() {
        cpu.freeze();
        memory.load("0x0CA12E", hardDrive.read("1028", 25478));
        cpu.jump("0x0CA12E");
        cpu.execute();
    }
}