import Image from "next/image";
import { SearchBar } from "@/components/searchBar/searchBar";
import { HotelCard } from "@/components/hotels/hotelCard";

export default function SearchHome() {
  return (
    <section className="relative min-h-[calc(100vh-4.813rem)]">
      <Image
        src="/home-bg.jpg"
        alt="Decorative Background"
        quality={100}
        fill
        className="object-cover -z-10 fixed"
        priority
      />
      <div className="container flex flex-col py-8 gap-y-10">
        <SearchBar />
        <HotelCard
          name="Hotel Name"
          description="
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Provident nisi eos molestiae, placeat suscipit veniam rerum aliquid, ipsa, totam officiis iusto quam tenetur incidunt omnis unde. Neque consequatur autem quae."
          location="Hotel Location"
          reviewCount={5}
          reviewAverage={4.5}
        />
      </div>
    </section>
  );
}
