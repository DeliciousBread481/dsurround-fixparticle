# DynamicSurroundings FixParticle
  
修复 [Dynamic Surroundings](https://github.com/OreCruncher/DynamicSurroundingsFabric) 模组中粒子渲染时的 `NullPointerException` 崩溃。  
  
## 解决的问题

```
java.lang.NullPointerException: Cannot invoke "net.minecraft.client.particle.TextureSheetParticle.render(...)" because "p" is null
at org.orecruncher.dsurround.effects.particles.ParticleRenderCollection.lambda$render$2(ParticleRenderCollection.java:72)
at org.orecruncher.dsurround.lib.collections.ObjectArray.forEach(ObjectArray.java:94)
```

### 原因  
  
`ParticleRenderCollection` 内部使用的 `ObjectArray` 容器在 `remove0()` 方法中会先将数组槽位置为 `null`，再移动元素。  
当 `tick()`（执行 `removeIf` 删除死亡粒子）和 `render()`（执行 `forEach` 遍历渲染）并发访问同一个数组时，  
`forEach` 可能读到被置为 `null` 的槽位，导致 NPE 崩溃。  

**此报错可能在有异步粒子相关功能的模组情况下能够复现 如`Ruok`或`Async Particle`**
  
### 修复方式  
  
通过 Mixin 注入 `ObjectArray.forEach()` 方法，在遍历时跳过 `null` 元素，防止 NPE。  
  
## 使用方法  
  
1. 下载 jar 文件  
2. 放入 `.minecraft/mods/` 目录  
3. 需要同时安装 Dynamic Surroundings 模组  
  
## 环境  
  
- Minecraft 1.21.1  
- NeoForge 21.1.84+  
- Dynamic Surroundings 0.4.0+
