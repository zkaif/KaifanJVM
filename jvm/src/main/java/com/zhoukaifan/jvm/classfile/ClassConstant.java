package com.zhoukaifan.jvm.classfile;

import com.zhoukaifan.jvm.classfile.constantimpl.*;
import lombok.Getter;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@Getter
public class ClassConstant {
    public static final ClassConstant UNDEFINED;
    private byte type;
    static {
        UNDEFINED = new ClassConstant((byte) 0);
    }

    protected ClassConstant(byte type) {
        this.type = type;
    }

    public static abstract class Builder {
        public abstract ClassConstant buildByByteBuffer(ByteBuffer byteBuffer);

        public static class BuilderUTF8 extends Builder {
            private static BuilderUTF8 BUILDER = new BuilderUTF8();
            @Override
            public ClassConstant buildByByteBuffer(ByteBuffer byteBuffer) {
                short length = byteBuffer.getShort();
                byte[] bytes = new byte[length];
                byteBuffer.get(bytes);
                String content  = new String(bytes, Charset.forName("UTF-8"));
                ClassConstantUTF8 classConstant =
                        new ClassConstantUTF8(length,content);
                return classConstant;
            }
        }
        public static class BuilderInteger extends Builder {
            private static BuilderInteger BUILDER = new BuilderInteger();
            @Override
            public ClassConstant buildByByteBuffer(ByteBuffer byteBuffer) {
                int content  = byteBuffer.getInt();
                ClassConstantInteger classConstant =
                        new ClassConstantInteger(content);
                return classConstant;
            }
        }
        public static class BuilderFloat extends Builder {
            private static BuilderFloat BUILDER = new BuilderFloat();
            @Override
            public ClassConstant buildByByteBuffer(ByteBuffer byteBuffer) {
                float content  = byteBuffer.getFloat();
                ClassConstantFloat classConstant =
                        new ClassConstantFloat(content);
                return classConstant;
            }
        }
        public static class BuilderLong extends Builder {
            private static BuilderLong BUILDER = new BuilderLong();
            @Override
            public ClassConstant buildByByteBuffer(ByteBuffer byteBuffer) {
                long content  = byteBuffer.getLong();
                ClassConstantLong classConstant =
                        new ClassConstantLong(content);
                return classConstant;
            }
        }
        public static class BuilderDouble extends Builder {
            private static BuilderDouble BUILDER = new BuilderDouble();
            @Override
            public ClassConstant buildByByteBuffer(ByteBuffer byteBuffer) {
                double content  = byteBuffer.getDouble();
                ClassConstantDouble classConstant =
                        new ClassConstantDouble(content);
                return classConstant;
            }
        }
        public static class BuilderClass extends Builder {
            private static BuilderClass BUILDER = new BuilderClass();
            @Override
            public ClassConstant buildByByteBuffer(ByteBuffer byteBuffer) {
                short index = byteBuffer.getShort();
                ClassConstantClass classConstant =
                        new ClassConstantClass(index);
                return classConstant;
            }
        }
        public static class BuilderString extends Builder {
            private static BuilderString BUILDER = new BuilderString();
            @Override
            public ClassConstant buildByByteBuffer(ByteBuffer byteBuffer) {
                short stringIndex = byteBuffer.getShort();
                ClassConstantString classConstant =
                        new ClassConstantString(stringIndex);
                return classConstant;
            }
        }
        public static class BuilderFieldref extends Builder {
            private static BuilderFieldref BUILDER = new BuilderFieldref();
            @Override
            public ClassConstant buildByByteBuffer(ByteBuffer byteBuffer) {
                short classIndex = byteBuffer.getShort();
                short nameAndTypeIndex = byteBuffer.getShort();
                ClassConstantFieldref classConstant =
                        new ClassConstantFieldref(classIndex,nameAndTypeIndex);
                return classConstant;
            }
        }
        public static class BuilderMethoderf extends Builder {
            private static BuilderMethoderf BUILDER = new BuilderMethoderf();
            @Override
            public ClassConstant buildByByteBuffer(ByteBuffer byteBuffer) {
                short classIndex = byteBuffer.getShort();
                short nameAndTypeIndex = byteBuffer.getShort();
                ClassConstantMethoderf classConstant =
                        new ClassConstantMethoderf(classIndex,nameAndTypeIndex);
                return classConstant;
            }
        }
        public static class BuilderInterfaceMethodref extends Builder {
            private static BuilderInterfaceMethodref BUILDER = new BuilderInterfaceMethodref();
            @Override
            public ClassConstant buildByByteBuffer(ByteBuffer byteBuffer) {
                short classIndex = byteBuffer.getShort();
                short nameAndTypeIndex = byteBuffer.getShort();
                ClassConstantInterfaceMethodref classConstant =
                        new ClassConstantInterfaceMethodref(classIndex,nameAndTypeIndex);
                return classConstant;
            }
        }
        public static class BuilderNameAndType extends Builder {
            private static BuilderNameAndType BUILDER = new BuilderNameAndType();
            @Override
            public ClassConstant buildByByteBuffer(ByteBuffer byteBuffer) {
                short nameIndex = byteBuffer.getShort();
                short descriptorIndex = byteBuffer.getShort();
                ClassConstantNameAndType classConstant =
                        new ClassConstantNameAndType(nameIndex,descriptorIndex);
                return classConstant;
            }
        }
        public static class BuilderMethodHandle extends Builder {
            private static BuilderMethodHandle BUILDER = new BuilderMethodHandle();
            @Override
            public ClassConstant buildByByteBuffer(ByteBuffer byteBuffer) {
                byte referenceKind = byteBuffer.get();
                short referenceIndex = byteBuffer.getShort();
                ClassConstantMethodHandle classConstant =
                        new ClassConstantMethodHandle(referenceKind,referenceIndex);
                return classConstant;
            }
        }
        public static class BuilderMethodType extends Builder {
            private static BuilderMethodType BUILDER = new BuilderMethodType();
            @Override
            public ClassConstant buildByByteBuffer(ByteBuffer byteBuffer) {
                short descriptorIndex = byteBuffer.getShort();
                ClassConstantMethodType classConstant =
                        new ClassConstantMethodType(descriptorIndex);
                return classConstant;
            }
        }
        public static class BuilderDynamic extends Builder {
            private static BuilderDynamic BUILDER = new BuilderDynamic();
            @Override
            public ClassConstant buildByByteBuffer(ByteBuffer byteBuffer) {
                short bootstrapMethodAttrIndex = byteBuffer.getShort();
                short nameAndTypeIndex = byteBuffer.getShort();
                ClassConstantDynamic classConstant =
                        new ClassConstantDynamic(bootstrapMethodAttrIndex,nameAndTypeIndex);
                return classConstant;
            }
        }
        public static class BuilderInvokeDynamic extends Builder {
            private static BuilderInvokeDynamic BUILDER = new BuilderInvokeDynamic();
            @Override
            public ClassConstant buildByByteBuffer(ByteBuffer byteBuffer) {
                short bootstrapMethodAttrIndex = byteBuffer.getShort();
                short nameAndTypeIndex = byteBuffer.getShort();
                ClassConstantInvokeDynamic classConstant =
                        new ClassConstantInvokeDynamic(bootstrapMethodAttrIndex,nameAndTypeIndex);
                return classConstant;
            }
        }
        public static class BuilderModule extends Builder {
            private static BuilderModule BUILDER = new BuilderModule();
            @Override
            public ClassConstant buildByByteBuffer(ByteBuffer byteBuffer) {
                short nameIndex = byteBuffer.getShort();
                ClassConstantModule classConstant =
                        new ClassConstantModule(nameIndex);
                return classConstant;
            }
        }
        public static class BuilderPackage extends Builder {
            private static BuilderPackage BUILDER = new BuilderPackage();
            @Override
            public ClassConstant buildByByteBuffer(ByteBuffer byteBuffer) {
                short nameIndex = byteBuffer.getShort();
                ClassConstantPackage classConstant =
                        new ClassConstantPackage(nameIndex);
                return classConstant;
            }
        }
    }
    public static Builder newBuilder(byte type){
        switch (type){
            case 1:
                return Builder.BuilderUTF8.BUILDER;
//            case 2:
//                return null;
            case 3:
                return Builder.BuilderInteger.BUILDER;
            case 4:
                return Builder.BuilderFloat.BUILDER;
            case 5:
                return Builder.BuilderLong.BUILDER;
            case 6:
                return Builder.BuilderDouble.BUILDER;
            case 7:
                return Builder.BuilderClass.BUILDER;
            case 8:
                return Builder.BuilderString.BUILDER;
            case 9:
                return Builder.BuilderFieldref.BUILDER;
            case 10:
                return Builder.BuilderMethoderf.BUILDER;
            case 11:
                return Builder.BuilderInterfaceMethodref.BUILDER;
            case 12:
                return Builder.BuilderNameAndType.BUILDER;
//            case 13:
//                return null;
//            case 14:
//                return null;
            case 15:
                return Builder.BuilderMethodHandle.BUILDER;
            case 16:
                return Builder.BuilderMethodType.BUILDER;
            case 17:
                return Builder.BuilderDynamic.BUILDER;
            case 18:
                return Builder.BuilderInvokeDynamic.BUILDER;
            case 19:
                return Builder.BuilderModule.BUILDER;
            case 20:
                return Builder.BuilderPackage.BUILDER;
            default:
                return null;
        }
    }
}
