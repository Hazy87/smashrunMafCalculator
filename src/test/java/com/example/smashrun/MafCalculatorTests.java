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

    @Test public void testCalculateTimeInMaf_75() throws IOException {
        var mafRangeProvider = mock(MafRangeProvider.class);
        when(mafRangeProvider.GetMafRange()).thenReturn(new MafRange(2.0,4.0));

        var myClass = new MafCalculator(mafRangeProvider);
        var mafTime = myClass.CalculateTimeInMaf(new ArrayList<>(Arrays.asList(2.0, 1.0, 2.0, 3.0)),new ArrayList<>(Arrays.asList(0.0, 1.0, 2.0, 3.0)));

        assertEquals(33,Math.round(mafTime));
    }

    @Test public void testCalculateTimeInMaf_50() throws IOException {
        var mafRangeProvider = mock(MafRangeProvider.class);
        when(mafRangeProvider.GetMafRange()).thenReturn(new MafRange(2.0,4.0));

        var myClass = new MafCalculator(mafRangeProvider);

        var mafTime = myClass.CalculateTimeInMaf(new ArrayList<>(Arrays.asList(1.0, 1.0, 2.0, 3.0)),new ArrayList<>(Arrays.asList(0.0, 1.0, 2.0, 3.0)));

        assertEquals(33, Math.round(mafTime));
    }

    @Test public void testCalculateTimeInMaf_50_withTime() throws IOException {
        var mafRangeProvider = mock(MafRangeProvider.class);
        when(mafRangeProvider.GetMafRange()).thenReturn(new MafRange(2.0,4.0));

        var myClass = new MafCalculator(mafRangeProvider);

        var mafTime = myClass.CalculateTimeInMaf(new ArrayList<>(Arrays.asList(1.0, 1.0, 2.0, 3.0,1.0,1.0,1.0)),new ArrayList<>(Arrays.asList(0.0, 1.0, 2.0, 3.0,10.0,15.0,20.0)));

        assertEquals(40, Math.round(mafTime));
    }



}

