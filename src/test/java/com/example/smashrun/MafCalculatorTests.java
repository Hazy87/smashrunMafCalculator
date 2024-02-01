package com.example.smashrun;

import com.example.smashrun.Maf.MafCalculator;
import com.example.smashrun.Maf.MafRange;
import com.example.smashrun.Maf.MafRangeProvider;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class MafCalculatorTests {

    @Test public void testCalculateTimeInMaf_50() throws IOException {
        var mafRangeProvider = mock(MafRangeProvider.class);
        when(mafRangeProvider.GetMafRange()).thenReturn(new MafRange(2,4));

        var myClass = new MafCalculator(mafRangeProvider);
        var mafTime = myClass.CalculateTimeInMaf(new ArrayList<>(Arrays.asList(2, 1, 2, 3)));

        assertEquals(mafTime, 50);
    }

    @Test public void testCalculateTimeInMaf_25() throws IOException {
        var mafRangeProvider = mock(MafRangeProvider.class);
        when(mafRangeProvider.GetMafRange()).thenReturn(new MafRange(2,4));

        var myClass = new MafCalculator(mafRangeProvider);

        var mafTime = myClass.CalculateTimeInMaf(new ArrayList<>(Arrays.asList(1, 1, 2, 3)));

        assertEquals(mafTime, 25);
    }



}

