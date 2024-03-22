import Image from "next/image";
// Data
import { getCities } from "@/lib/data";
// Components
import { SearchBar } from "@/components/searchBar/searchBar";
import { Footer } from "@/components/home/footer";

export default async function Home() {
  const cities = await getCities();

  return (
    <>
      <section className="flex relative h-[calc(100vh-4.813rem)]">
        <Image
          src="/home-bg.jpg"
          alt="Decorative Background"
          quality={100}
          fill
          className="object-cover -z-10"
          priority
        />
        <SearchBar locations={cities}/>
      </section>
      <Footer />
    </>
  );
}
