import Image from "next/image";
import { SearchBar } from "@/components/searchBar/searchBar";

export default function Home() {
  return (
    <section className="flex relative h-[calc(100vh-4.813rem)]">
      <Image
        src="/home-bg.jpg"
        alt="Decorative Background"
        quality={100}
        fill
        className="object-cover -z-10"
        priority
      />
      <SearchBar />
    </section>
  );
}
