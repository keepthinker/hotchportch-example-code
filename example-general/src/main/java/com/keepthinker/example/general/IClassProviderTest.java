package com.keepthinker.example.general;

import org.springframework.core.GenericTypeResolver;

public class IClassProviderTest {
    interface Provider<T> {
        default Class<T> provideClass() {
            return (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), Provider.class);
        }
    }

    interface I0<T> extends Provider<T> {
    }

    interface I1<T> extends I0<T> {
    }

    interface I2<T> extends I1<T> {
    }

    class C0<T> implements Provider<T> {

    }

    class C1<T> extends C0<T> {

    }

    class C2<T> extends C1<T> {

        public T func1 () {
            return null;
        }

    }

    class Entity {

    }

    public static void main(String[] args) {
        IClassProviderTest test = new IClassProviderTest();
        test.provideClass();

    }

    void provideClass() {
        {
            class C implements I0<Entity> {
            }
            System.out.println(new C().provideClass());
        }
        {
            class C implements I1<Entity> {
            }
            System.out.println(new C().provideClass());
        }
        {
            class C implements I2<Entity> {
            }
            System.out.println(new C().provideClass());
        }
        {
            class C extends C0<Entity> {
            }
            System.out.println(new C().provideClass());
        }
        {
            class C extends C1<Entity> {
            }
            System.out.println(new C().provideClass());
        }
        {
            class C extends C2<Entity> {
            }
            System.out.println(new C().provideClass());
        }
    }
}