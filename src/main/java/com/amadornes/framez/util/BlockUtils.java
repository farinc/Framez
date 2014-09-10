package com.amadornes.framez.util;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import codechicken.multipart.TileMultipart;

public class BlockUtils {

    public static void removeTileEntity(World world, int x, int y, int z) {

        TileEntity te = world.getTileEntity(x, y, z);
        if (te == null)
            return;

        Chunk chunk = world.getChunkFromChunkCoords(x >> 4, z >> 4);
        if (chunk != null) {
            ChunkPosition chunkposition = new ChunkPosition(x & 15, y, z & 15);

            if (chunk.isChunkLoaded) {
                chunk.chunkTileEntityMap.remove(chunkposition);
                if (!(te instanceof TileMultipart)) {
                    try {
                        world.loadedTileEntityList.remove(te);
                    } catch (Exception ex) {
                    }
                }
            }
        }
    }

    public static void setBlockSneaky(World world, int x, int y, int z, Block block) {

        Chunk chunk = world.getChunkFromChunkCoords(x >> 4, z >> 4);
        if (chunk != null) {
            ExtendedBlockStorage ebs = chunk.getBlockStorageArray()[y >> 4];
            ebs.func_150818_a(x >> 4, y & 15, z >> 4, block);
        }
    }

    public static void setBlockMetadataSneaky(World world, int x, int y, int z, int meta) {

        Chunk chunk = world.getChunkFromChunkCoords(x >> 4, z >> 4);
        if (chunk != null) {
            ExtendedBlockStorage ebs = chunk.getBlockStorageArray()[y >> 4];
            ebs.setExtBlockMetadata(x >> 4, y & 15, z >> 4, meta);
        }
    }
}
