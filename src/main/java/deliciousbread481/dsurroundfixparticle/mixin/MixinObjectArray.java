package deliciousbread481.dsurroundfixpaticle.mixin;  
  
import org.spongepowered.asm.mixin.Mixin;  
import org.spongepowered.asm.mixin.Shadow;  
import org.spongepowered.asm.mixin.injection.At;  
import org.spongepowered.asm.mixin.injection.Inject;  
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;  
  
import java.util.function.Consumer;  

@Mixin(targets = "org.orecruncher.dsurround.lib.collections.ObjectArray", remap = false)  
public class MixinObjectArray {  
  
    @Shadow  
    protected Object[] data;  
  
    @Shadow  
    protected int insertionIdx;  
  
    @SuppressWarnings({"rawtypes", "unchecked"})  
    @Inject(method = "forEach", at = @At("HEAD"), cancellable = true, remap = false)  
    private void dsurroundfix_nullSafeForEach(Consumer consumer, CallbackInfo ci) {  
        for (int i = 0; i < this.insertionIdx; i++) {  
            Object element = this.data[i];  
            if (element != null) {  
                consumer.accept(element);  
            }  
        }  
        ci.cancel();  
    }  
}