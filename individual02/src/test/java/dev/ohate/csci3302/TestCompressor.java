package dev.ohate.csci3302;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCompressor {

    private Compressor compressor;

    @BeforeEach
    public void setUp() {
        this.compressor = new Compressor();
    }

    @Test
    public void repeatString() {
        assertEquals("", this.compressor.repeatString("dog", 0));
        assertEquals("cat", this.compressor.repeatString("cat", 1));
        assertEquals("hathat", this.compressor.repeatString("hat", 2));
    }

    @Test
    public void compress() {
        assertEquals("A3b3C3d3E3", this.compressor.compress("AAAbbbCCCdddEEE"));
        assertEquals("b1a24c1", this.compressor.compress("baaaaaaaaaaaaaaaaaaaaaaaac"));
        assertEquals("H1e1l2o1 1W1o1r1l1d1", this.compressor.compress("Hello World"));
    }

    @Test
    public void decompress() {
        assertEquals("AAAbbbCCCdddEEE", this.compressor.decompress("A3b3C3d3E3"));
        assertEquals("baaaaaaaaaaaaaaaaaaaaaaaac", this.compressor.decompress("b1a24c1"));
        assertEquals("Hello World", this.compressor.decompress("H1e1l2o1 1W1o1r1l1d1"));
    }

}
