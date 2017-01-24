package com.shaznee.cracker.extensions.pdf;

/**
 * An interface to allow data to be stored completely in memory or
 * to use a scratch file on the disk.
 *
 * @author Ben Litchfield
 */
public interface RandomAccess extends RandomAccessRead, RandomAccessWrite
{
    // super interface for both read and write
}