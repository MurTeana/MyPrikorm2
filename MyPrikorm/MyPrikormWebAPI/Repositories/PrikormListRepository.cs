using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using MyPrikormWebAPI.Interfaces;
using MyPrikormWebAPI.DB.Context;
using MyPrikormWebAPI.DB.Entities;

namespace MyPrikormWebAPI.Repositories
{
    public class PrikormListRepository : IRepositoryPrikormList
    {
        private ApplicationContext db;
        public PrikormListRepository(ApplicationContext context)
        {
            db = context;
        }

        public async Task<List<PrikormList>> GetAll()
        {
            return await db.PrikormLists.ToListAsync();
        }

        public async Task<PrikormList> Get(int id)
        {
            return await db.PrikormLists.FirstOrDefaultAsync(x => x.Id == id);
        }

        public async Task<PrikormList> Create(PrikormList prikormlist)
        {
            if (prikormlist != null)
            {
                db.PrikormLists.Add(prikormlist);
                await db.SaveChangesAsync();
            }
            return prikormlist;
        }

        public async Task<PrikormList> Update(PrikormList prikormlist)
        {
            if (!db.PrikormLists.Any(x => x.Id == prikormlist.Id))
            {
                return null;
            }

            db.Update(prikormlist);
            await db.SaveChangesAsync();
            return prikormlist;
        }

        public async Task<PrikormList> Delete(int id)
        {
            PrikormList prikormlist = db.PrikormLists.FirstOrDefault(x => x.Id == id);
            if (prikormlist == null)
            {
                return prikormlist;
            }

            db.PrikormLists.Remove(prikormlist);
            await db.SaveChangesAsync();
            return prikormlist;
        }
    }
}
