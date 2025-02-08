package com.example.monitoringsystem.tool;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class SystemMetrics {

    private static final OperatingSystemMXBean osBean =
        (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    // 获取操作系统的CPU使用率
    public static double getCpuUsage() {
        return osBean.getSystemCpuLoad() * 100; // 转换为百分比
    }

    // 获取操作系统的内存使用情况
    public static double getMemoryUsage() {
        long totalMemory = osBean.getTotalPhysicalMemorySize();  // 总物理内存
        long freeMemory = osBean.getFreePhysicalMemorySize();   // 空闲物理内存
        return ((double) (totalMemory - freeMemory) / totalMemory) * 100; // 转换为百分比
    }

    // 获取JVM的内存使用情况
    public static double getJvmMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        return ((double) (totalMemory - freeMemory) / totalMemory) * 100; // 转换为百分比
    }
}
